(function( $ ) {
	$.Shop = function( element ) {
		this.$element = $( element );
		this.init();
	};


$Shop.prototype = {
	init: function() {
		
		this.cartPrefix = "e-prodavnica ";
		
		this.cartName = "e-kosnicka";
		
		this.total = "total";
		
		this.storage = sessionStorage;
		
		this.$formAddToCart = this.$element.find( "form.add-to-cart" );
		
		this.$formCart = this.$element.find( "#e-kosnicka" );
		
		this.$subTotal = this.$element.find( "#total" );
		
		this.$emptyCartBtn = this.$element.find( "#empty_cart" );
		
		this.currencyString = "МКД";
		
		this.requiredFields = {
			expression: {
				value: /^([w-.]+)@((?:[w]+.)+)([a-z]){2,4}$/
			},
			
			str: {
				value: ""
			}
			
		};
		
		this.createCart();
		this.handleAddToCartForm();
		this.emptyCart();
		this.displayCart();
	
	},
		
		createCart: function() {
			if(this.storage.getItem( this.cartName ) == null ) {
				
				var cart = {};
				cart.items = [];
				
				this.storage.setItem( this.cartName, this._toJSONString ( cart ) );
				this.storage.setItem( this.total, "0" );
			}
		},
		
		emptyCart: function() {
			var self = this;
			if( self.$emptyCartBtn.length ) {
				self.$emptyCartBtn.on( "click", function() {
					self._emptyCart();
				});
			}
		},
		
		handleAddToCartForm: function() {
			var self = this;
			self.$formAddToCart.each(function() {
				var $form = $( this );
				var $product = $form.parent();
				var price = self._convertString( $product.data( "price" ) );
				var name = $product.data( "name" );
				
				$form.on( "submit", function() {
					var qty = self.convertString( $form.find( ".qty" ).val() );
					var total = qty * price;
					// var total = self._convertString( $self.storage.getItem ( self.total ) );
					var sTotal = total;
					self.storage.setItem( self.total, sTotal );
					self._addToCart({
						product: name,
						price: price,
						qty: qty,
						total: total
					});
					
				});
			});
		},
		
		displayCart: function() {
			if( this.$formCart.length ) {
				var cat = this._toJSONObject( this.storage.getItem ( this.cartName ) );
				var items = cart.items;
				var $tableCart = this.$formCart.find( ".e-kosnicka" );
				var $tableCartBody = $tableCart.find( "tbody" );
				
				for( var i=0; i < items.length; ++i ) {
					var item = items[i];
					var product = item.product;
					var price = item.price + " " + this.currencyString;
					var qty = item.qty;
					var total = item.total;
					var html = "<tr><td class='pname'>" + product + "</td>" + "<td class='pqty'><input type='text' value='" + qty + "' class='qty'/></td>" + "<td class='pprice'>" + price + "</td>" + "<td class='ptotal'>" + total + "</td></tr>";
					
					$tableCartBody.html( $tableCartBody.html() + html );
				}
				
				var total = this.storage.getItem( this.total );
				
				for( var j = 0; j < cartItems.length; ++j ) {
					var cartItem = cartItems[j];
					var cartProduct = cartItem.product;
					var cartPrice = cartItem.price + " " + this.currencyString;
					var cartQty = cartItem.qty;
					var cartTotal = cartItem.total;
					var cartHTML = "<tr><td class='pname'>" + cartProduct + "</td>" + "<td class='pqty'>" + cartQty + "</td>" + "<td class='pprice'>" + cartPrice + "</td>" + "<td class='ptotal'>" + cartTotal + "</td></tr>";
					
					$cartBody.html( $cartBody.html() + cartHTML );
				}
				
				var cartTotal = this.storage.getItem( this.total );
			}
		},
	
	_emptyCart: function() {
		this.storage.clear();
	},
	
	_formatNumber: function( num, places ) {
		var n = num.toFixed( places );
		return n;
	},
	
	_extractPrice: function( element ) {
		var self = this;
		var text = element.text;
		var price = text.replace( self.currencyString, "").replace( " ", "" );
		return price;
	},
	
	_convertString: function( numStr ) {
		var num;
		if( /^[-+]?[0-9]+.[0-9]+$/.test( numStr ) ) {
			num = parseFloat( numStr );
		} else if( /^d+$/.test( numStr ) ) {
			num = parseInt( numStr );
		} else {
			num = Number( numStr );
		}

		if( !isNaN( num ) ) {
			return num;
		} else {
			console.warn( numStr + " cannot be converted into a number" );
			return false;
		}
	},
	
	_convertNumber: function( n ) {
		var str = n.toString();
		return str;
	},
	
	_toJSONObject: function( str ) {
		var obj = JSON.parse( str );
		return obj;
	},
	
	_toJSONString: function( obj ) {
		var str = JSON.stringify( obj );
		return str;
	},
	
	_addToCart: function( values ) {
		var cart = this.storage.getItem( this.cartName );
		var cartObject = this._toJSONObject( cart );
		var cartCopy = cartObject;
		var items = cartCopy.items;
		items.push ( values );
		
		this.storage.setItem(this.cartName, this._toJSONString( cartCopy ) );
	}
	
};


	$(function() {
		var shop = new $.Shop( "#site" );
	});

})( jQuery );

