(function(){
    'use strict';

    angular.module('dplm.filters.timeago', [])
        .filter('utcDateTimeAgo', function ($window) {
            return function (date) {
                var moment = $window.require('moment');
                moment.locale($window.localStorage.lang);
                if(typeof date === 'number'){
                    date = new Date(date).toISOString().slice(0,-1);
                }
                return moment(date + '+00:00').fromNow();
            };
        })
        .filter('localDateTimeAgo', function ($window) {
            return function (date) {
                var moment = $window.require('moment');
                moment.locale($window.localStorage.lang);
                if(typeof date === 'number'){
                    date = new Date(date);
                }
                return moment(date).fromNow();
            };
        });
})();
