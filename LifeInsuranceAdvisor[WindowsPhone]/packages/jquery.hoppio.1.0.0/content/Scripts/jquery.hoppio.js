/// <reference path="jquery-1.7.1-vsdoc.js" />
(function ($) {
	var namespace = "hoppio";
	var methods = {};

	var endPoint = "https://hopp.io/";
	var version = "v1";

	var _options = {
		base: endPoint + version
	};

	function _url() {
		var url = _options.base;
		for (var i = 0; i < arguments.length; i++)
			if (arguments[i] != null)
				url += "/" + arguments[i];
		return url;
	}

	function _auth(username, password) {
		var tok = username + ":" + (password === undefined ? "" : password);
		var hash = Base64.encode(tok);
		return "Basic " + hash;
	}

	function _creds() {
		if (_options.appKey) {
			return true;
		}

		var error = "Missing appKey authentication parameters.\n" +
				"Please pass the application key to $." + namespace + ".init({\n" +
				"    appKey = \"Your Application Key\"\n" +
				"});";

		$.error(error);

		return false;
	}

	function _error(jqXHR, textStatus, errorThrown) {
		$.error("$." + namespace + " :" + textStatus + " " + errorThrown);
	}

	function _http(action, url, data, callback) {
		if (!_creds())
			return false;

		callback = typeof callback === "function" ? { done: callback} : callback;

		var request = $.extend(
			typeof _options.ajaxSettings === "object" ? _options.ajaxSettings : {},
			{
				// data
				contentType: "application/json",
				processData: false,
				dataType: "json",
				data: data,

				// action
				url: url,
				type: action,

				// credentials
				headers: {
					"Authorization": _auth(_options.appKey, _options.sessionToken)
				}
			}, true);

		var ajax = $.ajax(request);

		if (callback.done && typeof callback.done === "function") ajax.done(callback.done);
		if (callback.fail && typeof callback.fail === "function") ajax.fail(callback.fail); else ajax.fail(_error);
		if (callback.always && typeof callback.always === "function") ajax.always(callback.always);

		return ajax;
	};

	methods.init = function (options) {
		$.extend(_options, typeof options === 'object' ? options : {}, true);
		return $[namespace];
	}

	methods.createObject = function (objectName, obj, callback) {
		return _http("post", _url("objects", objectName), JSON.stringify(obj), callback);
	}

	methods.createStaticObject = function (objectName, objectId, obj, callback) {
		return _http("post", _url("objects", objectName, objectId), JSON.stringify(obj), callback);
	}

	methods.getObject = function (objectName, objectId, callback) {
		return _http("get", _url("objects", objectName, objectId), false, callback);
	}

	methods.updateObject = function (objectName, objectId, obj, callback) {
		return _http("put", _url("objects", objectName, objectId), JSON.stringify(obj), callback);
	}

	methods.deleteObject = function (objectName, objectId, callback) {
		return _http("delete", _url("objects", objectName, objectId), false, callback);
	}

	methods.queryObjects = function (objectName, params, callback) {
		var urlQuery = {}
		if (params.where && typeof params.where === "object") urlQuery.where = JSON.stringify(params.where);
		if (params.where && typeof params.where !== "object") urlQuery.where = params.where;
		if (params.skip) urlQuery.where = params.skip;
		if (params.take) urlQuery.where = params.take;
		if (params.order) urlQuery.where = params.order;

		return _http("get", _url("objects", objectName), $.param(urlQuery, true), callback);
	}

	methods.createUser = function (obj, callback) {
		return _http("post", _url("users"), JSON.stringify(obj), callback);
	}

	methods.getUser = function (objectId, callback) {
		return _http("get", _url("users", objectId), false, callback);
	}

	methods.updateUser = function (objectId, obj, callback) {
		return _http("put", _url("users", objectId), JSON.stringify(obj), callback);
	}

	methods.deleteUser = function (objectId, callback) {
		return _http("delete", _url("users", objectId), false, callback);
	}

	methods.queryUsers = function (params, callback) {
		var urlQuery = {}
		if (params.where && typeof params.where === "object") urlQuery.where = JSON.stringify(params.where);
		if (params.where && typeof params.where !== "object") urlQuery.where = params.where;
		if (params.skip) urlQuery.where = params.skip;
		if (params.take) urlQuery.where = params.take;
		if (params.order) urlQuery.where = params.order;

		return _http("get", _url("users"), $.param(urlQuery, true), callback);
	}

	$[namespace] = methods;
})(jQuery);

var Base64 = {

	// private property
	_keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",

	// public method for encoding
	encode: function (input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;

		input = Base64._utf8_encode(input);

		while (i < input.length) {

			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);

			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;

			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}

			output = output +
			this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
			this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);

		}

		return output;
	},

	// private method for UTF-8 encoding
	_utf8_encode : function (string) {
		string = string.replace(/\r\n/g,"\n");
		var utftext = "";
 
		for (var n = 0; n < string.length; n++) {
 
			var c = string.charCodeAt(n);
 
			if (c < 128) {
				utftext += String.fromCharCode(c);
			}
			else if((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			}
			else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}
 
		}
 
		return utftext;
	}
};