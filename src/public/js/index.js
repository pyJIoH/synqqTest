(function() {
	var app = angular.module('app', []).controller('ctrl',
			function($scope, $http) {
				$scope.start = function() {
					$http.post('/start')
			            .success(function (data, status, headers, config) {
			            	$scope.startDisabled = true;
			            	$scope.uuid = data.uuid;
		            	})
			            .error(function (data, status, header, config) {
			            	alert('Start failed');
			            });
				}

				$scope.stop = function() {
			        var data = $.param({
			            json: JSON.stringify({
			                uuid: $scope.uuid
			            })
			        });
					$http.post('/stop', data)
			            .success(function (data, status, headers, config) {
			            	$scope.startDisabled = false;
			            })
			            .error(function (data, status, header, config) {
			            	alert('Stop failed');
			            });
				}
			});
})()