(function (w) {
    var getGroupWidth = function (group) {
        return ifNullGetDefault(group.width, Math.round((group.tiles.length / 2)) * (getDefaultTileWidthHeight(null) + 10));
    };

    var ifNullGetDefault = function (maybeNull, defaultValue) {
        if (maybeNull == null)
            return defaultValue;
        return maybeNull;
    };

    var getDefaultGroupWidth = function (maybeNull) {
        return ifNullGetDefault(maybeNull, null);
    };

    var getDefaultBackgroundColor = function (maybeNull) {
        return ifNullGetDefault(maybeNull, 'transparent');
    };

    var getDefaultString = function (maybeNull) {
        return ifNullGetDefault(maybeNull, '');
    };

    var getDefaultTileWidthHeight = function (maybeNull) {
        return parseInt(ifNullGetDefault(maybeNull, 250));
    };

    var setTileWidthAndHight = function (currentTile) {
        var size = getDefaultString(currentTile.data('size'));
        var tileWidth;
        var tileHeight;
        if (size == 'small') {
            tileWidth = 55;
            tileHeight = 55;
        }
        else if (size == 'medium') {
            tileWidth = 120;
            tileHeight = 120;
        }
        else if (size == 'large') {
            tileWidth = 250;
            tileHeight = 250;
        }
        else if (size == 'wide') {
            tileWidth = 250;
            tileHeight = 120;
        } else {
            tileWidth = getDefaultTileWidthHeight(currentTile.data('width'));
            tileHeight = getDefaultTileWidthHeight(currentTile.data('height'));
        }

        return { tileWidth: tileWidth, tileHeight: tileHeight };
    };

    var liveTileCount = 0;
    var getTiles = function (tilesContainer, encapsulateInLiveTile) {
        var groupTiles = tilesContainer.find('> [data-type=tile], > [data-type=livetile]');
        var allTiles = new Array();
        var tileObject = null;
        var tileTitle = null;
        var tileWidth = null;
        var tileHeight = null;
        var tileBg = null;
        var tileLink = null;
        var tileSubtitle = null;

        if (arguments.length < 2) {
            encapsulateInLiveTile = true;
        }

        for (var t = 0; t < groupTiles.length; t++) {
            var currentTile = $(groupTiles[t]);
            var dimention = setTileWidthAndHight(currentTile);

            tileTitle = getDefaultString(currentTile.data('title'));
            tileSubtitle = getDefaultString(currentTile.data('subtitle'));
            tileBg = getDefaultBackgroundColor(currentTile.data('backgroundcolor'));
            tileLink = getDefaultString(currentTile.data('link'));
            tileHeight = dimention.tileHeight;
            tileWidth = dimention.tileWidth;




            if (currentTile.data('type') == 'tile') {

                //if (encapsulateInLiveTile == false || !(tileTitle || tileSubtitle)) {
                if (encapsulateInLiveTile == false) {
                    tileObject = new Tile();

                    tileObject.htmlContent = currentTile.html();
                    tileObject.image = getDefaultString(currentTile.data('image'));
                } else {
                    var realTileObject = new Tile();
                    realTileObject.htmlContent = currentTile.html();
                    realTileObject.image = getDefaultString(currentTile.data('image'));

                    realTileObject.prototype.width = tileWidth;
                    realTileObject.prototype.height = tileHeight;
                    //realTileObject.prototype.title = tileTitle;
                    //realTileObject.prototype.subtitle = tileSubtitle;
                    realTileObject.prototype.backgroundColor = tileBg;
                    //realTileObject.prototype.link = tileLink;

                    tileObject = new LiveTile(++liveTileCount, false);
                    tileObject.Tiles = new Array();
                    tileObject.Tiles[0] = realTileObject;
                }
            } else if (currentTile.data('type') == 'livetile') {
                tileObject = new LiveTile(++liveTileCount);
                tileObject.Tiles = getTiles(currentTile, false);
            } else {
                continue;
            }

            tileObject.prototype.width = tileWidth;
            tileObject.prototype.height = tileHeight;
            tileObject.prototype.title = tileTitle;
            tileObject.prototype.subtitle = tileSubtitle;
            tileObject.prototype.backgroundColor = tileBg;
            tileObject.prototype.link = tileLink;

            allTiles[t] = tileObject;
        }

        return allTiles;
    };

    var buildTile = function (oneTile) {
        var tile = '';
        if (oneTile.prototype.link) {
            tile += '<a href="' + oneTile.prototype.link + '">';
        }

        tile += '<div style="float: left;margin: 0px 10px 10px 0px;color: #FFFFFF;position: relative;height: ' + oneTile.prototype.height + 'px;width: ' + oneTile.prototype.width + 'px;background-color: ' + oneTile.prototype.backgroundColor + ';' + '">';

        tile += oneTile.buildTileHtml();

        tile += '</div>';

        if (oneTile.prototype.link) {
            tile += '</a>';
        }

        return tile;
    };

    var buildWindows8Style = function (pageInfo, groups, withAnimation) {
        var mainDivWidth = 0;

        for (var j = 0; j < groups.length; j++) {
            mainDivWidth += 60;
            mainDivWidth += getGroupWidth(groups[j]);
        }

        var mainDiv = $('<div class="mainDiv" style="width:' + mainDivWidth + 'px;"></div>');
        
        if (pageInfo.BackButton) {
            var backButton = '';
            if (pageInfo.TitleFixedLocation) {
                backButton =
                    $('<a href="' + pageInfo.BackButton + '"style="position:fixed;top:20px">' +
                        '    <div class="slidingAnimationBackPreposition" style="left: 60px;position: absolute;float: left;width: 50px;height: 50px;left:5px"></div>' +
                        '</a>');
                mainDiv.append(backButton);
            } else {
                backButton =
                   $('<a href="' + pageInfo.BackButton + '">' +
                       '    <div ' + (withAnimation ? 'style="left: 60px;position: absolute;float: left;width: 50px;height: 50px;transition-property: left;transition-duration: 0.6s;transition-timing-function: cubic-bezier(0,0,0.1,1);transition-delay: 0s;"' : 'style="left: 60px;position: absolute;float: left;width: 50px;height: 50px;left:5px"') + ' class="slidingAnimationBackPreposition"></div>' +
                       '</a>');
                mainDiv.append(backButton);
            }
        }

        var siteTitle;
        var siteTitleFixed;
        if (pageInfo.TitleFixedLocation) {
            siteTitle = $('<div style="position: relative;left: 240px;transition-property: left;transition-duration: 0.6s;transition-timing-function: cubic-bezier(0,0,0.1,1);transition-delay: 0s;width: 100%;" class="slidingAnimationFasterPreposition"><h1 class="titleH1" style="color:transparent" >' + pageInfo.Name + '</h1></div>');
            siteTitleFixed = $('<div style="width: 100%;transition-property: left;transition-duration: 0.6s;transition-timing-function: cubic-bezier(0,0,0.1,1);transition-delay: 0s;"><h1 class="titleH1" style="position:fixed;left:68px;top:0px">' + pageInfo.Name + '</h1></div>');
            mainDiv.append(siteTitleFixed);
            mainDiv.append(siteTitle);
        } else {
            siteTitle = $('<div style="width: 100%;" ' + (withAnimation ? 'style="transition-property: left;transition-duration: 0.6s;transition-timing-function: cubic-bezier(0,0,0.1,1);transition-delay: 0s;position: relative;left: 240px;"' : 'style="position: relative;left: 240px;left:60px"') + ' class="slidingAnimationFasterPreposition"><h1 class="titleH1">' + pageInfo.Name + '</h1></div>');
            mainDiv.append(siteTitle);
        }

        for (var i = 0; i < groups.length; i++) {
            var groupWidth = getGroupWidth(groups[i]);

            var groupHtml =
                '<div style="width:' + groupWidth + 'px;float: left;margin: 0px 60px 0px 0px;">' +
                '   <div ' + (withAnimation ? 'style="position: relative;left: 240px;width: 100%;transition-property: left;transition-duration: 0.6s;transition-timing-function: cubic-bezier(0,0,0.1,1);transition-delay: 0s;"' : 'style="left: 60px;width: 100%;position: relative;left: 240px;"') + ' class="slidingAnimationFasterPreposition">';

            if (groups[i].link) {
                groupHtml += '<a style="text-decoration: none;" href="' + groups[i].link + '">';
            }
            groupHtml +=
                '       <h1 class="groupTitleH1">' + groups[i].title + '</h1>';
            if (groups[i].link) {
                groupHtml += '</a>';
            }
            groupHtml +=
                '   </div>' +
                '</div>';

            var groupContinerDiv = $(groupHtml);
            
            var slide = $('<div ' + (withAnimation ? 'style="position: relative;left: 260px;transition-property: left, opacity;transition-duration: 0.8s, 0.6s;transition-delay: 0s, 0.1s;transition-timing-function: cubic-bezier(0.1,0.1,0.2,1), ease-in;opacity: 0;"' : 'style="position: relative;left: 260px;left:60px"') + ' class="slidingAnimationPreposition"></div>');
            for (var t = 0; t < groups[i].tiles.length; t++) {
                var oneTile = groups[i].tiles[t];

                var tile = buildTile(oneTile);

                slide.append(tile);
            }

            mainDiv.append(groupContinerDiv.append(slide));
        }

        return mainDiv;
    };

    var LiveTileAnimation = function (mainContainerClass, itemHeight, repeatInterval) {
        if (!repeatInterval || repeatInterval < 9000) {
            repeatInterval = 9000;
        }

        repeatInterval = repeatInterval / 3;

        this.State = 0;
        this.Apply = function () {
            var currentState = this.State;
            setInterval(function () {
                if ($('.' + mainContainerClass + ' .liveTileItem').length <= 1) {
                    return;
                }

                var container = $('.' + mainContainerClass);
                if (currentState == 0) {
                    container.css('top', '0px'); // only one time for ie and firefox
                    currentState = 1;
                }
                else if (currentState == 1) {
                    container.addClass('liveTileSlidingAnimation');
                    currentState = 2;
                }
                else if (currentState == 2) {
                    container.css("top", '-' + itemHeight + 'px');
                    currentState = 3;
                }
                else if (currentState == 3) {
                    container.removeClass('liveTileSlidingAnimation');
                    var firstTileItem = $('.' + mainContainerClass + ' .liveTileItem')[0];
                    container.append(firstTileItem);
                    container.css('top', '0px');
                    currentState = 1;
                }
            }, repeatInterval);
        };
    };

    var TemplateParser = function () {
        var pageTemplate = $('[data-type=windows8style]');
        this.pageInfo = {
            Name: getDefaultString(pageTemplate.data('title')),
            BackButton: getDefaultString(pageTemplate.data('back')),
            TitleFixedLocation: ifNullGetDefault(pageTemplate.data('titlefixedlocation'), true),
            BackgroundColor: getDefaultString(pageTemplate.data('backgroundcolor'), 'rgb(24, 28, 24)'),
        };

        var groupsQ = $('[data-type=windows8style] > [data-type=group]');
        this.groups = Array(groupsQ.length);

        for (var i = 0; i < groupsQ.length; i++) {
            var currentGroup = $(groupsQ[i]);

            this.groups[i] = {
                title: getDefaultString(currentGroup.data('title')),
                width: getDefaultGroupWidth(currentGroup.data('width')),
                link: getDefaultString(currentGroup.data('link')),
                tiles: getTiles(currentGroup),
            };
        }
    };

    var TileBase = function () {
        this.width = getDefaultTileWidthHeight(null),
        this.height = getDefaultTileWidthHeight(null),
        this.link = getDefaultString(null),
        this.backgroundColor = getDefaultBackgroundColor(null),
        this.title = getDefaultString(null),
        this.subtitle = getDefaultString(null),
        this.buildTileOverlayHtml = function () {
            var overlayResult = '';
            if (this.title || this.subtitle) {
                var tileOverlyTop = parseInt((this.height * 30 / 100));

                overlayResult =
                    '<div class="itemOverlayDiv" style="width: 100%;height: 30%;position: relative;overflow: hidden;top: -' + tileOverlyTop + 'px">' +
                        '   <h4 class="itemTitleH4">' + this.title + '</h4>' +
                        '   <h6 class="itemSubtitleH6" style="position: absolute;">' + this.subtitle + '</h6>' +
                        '</div>';
            }

            return overlayResult;
        };
    };

    var Tile = function () {
        this.htmlContent = getDefaultString(null);
        this.image = getDefaultString(null);

        this.buildTileHtml = function () {
            var tileHtml = '';

            if (this.htmlContent) {
                if (this.image) {
                    tileHtml += '<div style="float: left;width:' + this.prototype.width + 'px;height:' + this.prototype.height + 'px;background:url(\'' + this.image + '\');background-size:100% 100%;">' + this.htmlContent + '</div>';
                } else {
                    tileHtml += this.htmlContent;
                }
            } else {
                if (this.image) {
                    tileHtml += '<div style="width:' + this.prototype.width + 'px;height:' + this.prototype.height + 'px;background:url(\'' + this.image + '\');background-size:100% 100%;"></div>';
                } else {
                    tileHtml += '<div style="width: 100%;height: 100%;border: 0px;"></div>';
                }

                tileHtml += this.prototype.buildTileOverlayHtml();
            }

            return tileHtml;
        };

        this.prototype = new TileBase();
    };

    var LiveTile = function (liveTileCounter, realLiveTile) {

        var counter = liveTileCounter;

        var ignoreOverflow = false;

        if (arguments.length > 1) {
            ignoreOverflow = !realLiveTile;

        }

        this.Tiles = new Array();

        this.buildTileHtml = function () {
            var tileHtml = '';
            var liveTileAnimationClassName = "liveTileAnimation" + counter;
            tileHtml += '<div style="width:' + this.prototype.width + 'px; height:' + this.prototype.height + 'px;' + (ignoreOverflow ? '' : 'overflow: hidden">');
            tileHtml += '<div class="' + liveTileAnimationClassName + '" style="width:' + this.prototype.width + 'px; height:' + this.prototype.height + 'px;position:relative;float:left">';

            tileHtml += this.buildLiveTileItemHtml();

            tileHtml += '</div>';

            tileHtml += this.prototype.buildTileOverlayHtml();

            tileHtml += '</div>';

            //if (i > 1) {
            var anim = new LiveTileAnimation(liveTileAnimationClassName, this.prototype.height, (9000 + (liveTileCounter * 313)));
            anim.Apply();
            //}

            return tileHtml;
        };

        this.buildLiveTileItemHtml = function () {
            var tileHtml = '';
            for (var i = 0; i < this.Tiles.length; i++) {
                tileHtml += '<div class="liveTileItem" style="width:100%; height:100%;position: relative; background-color: ' + this.Tiles[i].prototype.backgroundColor + ';">';

                if (this.Tiles[i].prototype.link) {
                    tileHtml += '<a href="' + this.Tiles[i].prototype.link + '"><div style="width:100%; height:100%; float:left;">';
                }

                tileHtml += this.Tiles[i].buildTileHtml();

                if (this.Tiles[i].prototype.link) {
                    tileHtml += '</div></a>';
                }

                tileHtml += '</div>';
            }
            return tileHtml;
        };

        this.prototype = new TileBase();
    };

    w.applyWindows8Style = function (selector, animation, scrolling) {
        w.EnableHorizontalScrolling = false;
        var pageTemplate = $('[data-type=windows8style]');
        if (pageTemplate) {
            var template = new TemplateParser();
            pageTemplate.remove();
            if (!selector) {
                selector = 'body';
            }
            if (arguments.length < 2) {
                animation = true;
            }
            var theContiner = $(selector);
            theContiner.append(buildWindows8Style(template.pageInfo, template.groups, animation));
            theContiner.addClass('styleContainer');

            if (arguments.length > 2) {
                w.EnableHorizontalScrolling = scrolling;
            } else {
                w.EnableHorizontalScrolling = true;
            }

        }
    };

    w.updateLiveTile = function (liveTileClassName, newTileHtmlContent) {
        var liveTileObject = getTiles($('<div>' + newTileHtmlContent + '</div>'));
        for (var i = 0; i < liveTileObject.length; i++) {
            $("." + liveTileClassName).append(liveTileObject[i].buildLiveTileItemHtml());
        }
    };
})(window);

$(document).ready(function () {
    var hasVerticalScrollBar = function () {
        if (window.EnableHorizontalScrolling) {
            var delta = 0;
            if ($.browser.msie) {
                delta = 20; //hardcoded for ie ?? 
            }

            return $(document).height() > ($(window).height() + delta);
        }

        return false;
    };

    $(document).bind('mousewheel DOMMouseScroll', function (e) {
        e = e ? e : window.event;
        var docElement = $(document);
        if (!(hasVerticalScrollBar())) {
            var scrollValue = (e.originalEvent.detail) ? (e.originalEvent.detail * -1 * 40) : e.originalEvent.wheelDelta;
            docElement.scrollLeft(docElement.scrollLeft() - scrollValue);
            return false;
        }
        return true;
    });
});

$(document).ready(function () {
    setTimeout(function () {
        $('.slidingAnimationPreposition').css('left', '60px');
        $('.slidingAnimationFasterPreposition').css('left', '60px');
        $('.slidingAnimationBackPreposition').css('left', '5px');

        $('.slidingAnimationPreposition').css('opacity', '1');
    }, 50);
});
