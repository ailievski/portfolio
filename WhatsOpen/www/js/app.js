// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('WhatsOpen', ['ionic'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if(window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
})

function OrderFormController($scope){

	// Define the model properties. The view will loop
	// through the services array and genreate a li
	// element for every one of its items.

	$scope.venues = [
		{
			Title: 'XO Club',
			Longitude: 21.432150520101,
			Latitude: 41.996195649309,
			Distance: 27.0,
			Category: 'Nightclub',
			City: 'Skopje',
			Address: 'Плоштад Македонија',
			LogoUrl: 'https://ss3.4sqi.net/img/categories_v2/nightlife/nightclub_.png',
			IsOpen: true,
			OpenStatus: 'Open until 4:00am'
		},{
			Title: 'Rock Kafana Rustikana',
			Longitude: 21.4300428519629,
			Latitude: 41.9944103328292,
			Distance: 248.0,
			Category: 'BBQ Joint',
			City: 'Skopje',
			Address: 'Вељко Влаховиќ 5',
			LogoUrl: 'https://ss3.4sqi.net/img/categories_v2/food/bbq_.png',
			IsOpen: true,
			OpenStatus: 'Open until 1:00am'
		},{
			Title: 'Plaza de Toros',
			Longitude: 21.4350610971451,
			Latitude: 41.9952142255563,
			Distance: 290.0,
			Category: 'Restaurant',
			City: 'Skopje',
			Address: 'Кеј 13ти Ноември лок.65',
			LogoUrl: 'https://ss3.4sqi.net/img/categories_v2/food/default_.png',
			IsOpen: 'true',
			OpenStatus: 'Open until 01:00am'
		},{
			Title: 'Birokrat - Ministry of Coffee',
			Longitude: 21.4354634033321,
			Latitude: 41.9953123618411,
			Distance: 318.0,
			Category: 'Bar',
			City: 'Skopje',
			Address: 'Кеј 13-ти Ноември',
			LogoUrl: 'https://ss3.4sqi.net/img/categories_v2/nightlife/pub_.png',
			IsOpen: 'true',
			OpenStatus: 'Open until 04:00am'
		},{
			Title: 'Café Skopje',
			Longitude: 21.4313353997353,
			Latitude: 41.9953688103913,
			Distance: 102.0,
			Category: 'Café',
			City: 'Skopje',
			Address: 'Плоштад Македонија',
			LogoUrl: 'https://ss3.4sqi.net/img/categories_v2/food/cafe_.png',
			IsOpen: true,
			OpenStatus: 'Open until Noon'
		},{
			Title: 'Гостилница Дукат',
			Longitude: 21.413161,
			Latitude: 41.994923,
			Distance: 1549.0,
			Category: 'Restaurant',
			City: 'Skopje',
			Address: 'Теодосиј Гологанов 79',
			LogoUrl: 'https://ss3.4sqi.net/img/categories_v2/food/cafe_.png',
			IsOpen: true,
			OpenStatus: 'Open until Midnight'
		},{
			Title: 'Champagneria LOUNGE',
			Longitude: 21.4305726800687,
			Latitude: 41.995729449508,
			Distance: 116.0,
			Category: 'Lounge',
			City: 'Skopje',
			Address: 'Плоштад Македонија',
			LogoUrl: 'https://ss3.4sqi.net/img/categories_v2/nightlife/default_.png',
			IsOpen: 'true',
			OpenStatus: 'Open until 01:00am'
		},{
			Title: 'Up Cafe',
			Longitude: 21.4324474568341,
			Latitude: 41.995206181054,
			Distance: 124.0,
			Category: 'Café',
			City: 'Skopje',
			Address: 'Плоштад Македонија',
			LogoUrl: 'https://ss3.4sqi.net/img/categories_v2/food/cafe_.png',
			IsOpen: 'true',
			OpenStatus: 'Open until 01:00am'
		},{
			Title: 'Midnight Club',
			Longitude: 21.4357079396025,
			Latitude: 41.995188980701,
			Distance: 341.0,
			Category: 'Nightclub',
			City: 'Skopje',
			Address: 'Кеј 13-ти Ноември',
			LogoUrl: 'https://ss3.4sqi.net/img/categories_v2/nightlife/nightclub_.png',
			IsOpen: 'true',
			OpenStatus: 'Open until 04:00am'
		}
	];
}

WhatsOpen.service('dataService', function($http) {
delete $http.defaults.headers.common['X-Requested-With'];
this.getData = function() {
	return $http({
		method: 'JSONP',
		url: 'http://localhost:51582/api/whatsopen',
		params: 'lat=41.996218, lon=21.431815, cat=food, rad=250',
		});
	}
});

WhatsOpen.controller('AngularjJSCtrl', function($scope, dataService) {
	$scope.data = null;
	dataService.getData(function(dataResponse) {
		$scope.data = dataResponse;
	});
});
