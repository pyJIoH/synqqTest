(function() {
	var app = angular.module('app', []).controller('ctrl',
			function($scope, $http) {
				$scope.start = function() {
					$http.post('/generate10')
		            .success(function (data, status, headers, config) {
		            	alert('ok');
		            })
		            .error(function (data, status, header, config) {
		            });
				}

				$scope.stop = function() {
				}
			});
})()