(function() {
	var app = angular.module('app', []).controller('ctrl',
			function($scope, $http) {
				$scope.start = function() {
					$http.post('/start')
		            .success(function (data, status, headers, config) {
		            	$scope.startDisabled = true;
	            	})
		            .error(function (data, status, header, config) {
		            });
				}

				$scope.stop = function() {
					$http.post('/stop')
		            .success(function (data, status, headers, config) {
		            	$scope.startDisabled = false;
		            })
		            .error(function (data, status, header, config) {
		            });
				}
			});
})()