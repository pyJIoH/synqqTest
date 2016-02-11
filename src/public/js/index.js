(function() {
	var app = angular.module('app', []).controller('ctrl',
			function($scope, $http) {
				$scope.events = 10000;
				$scope.attendee = 10;
				
				$scope.start = function() {
					$http.post('/start?eventsAmount=' + $scope.events +'&attendeeMaxRange=' + $scope.attendee)
			            .success(function (data, status, headers, config) {
			            	$scope.startDisabled = true;
			            	$scope.uuid = data.uuid;
		            	})
			            .error(function (data, status, header, config) {
			            	alert('Start failed');
			            });
				}

				$scope.stop = function() {
					$http.post('/stop?uuid=' + $scope.uuid)
			            .success(function (data, status, headers, config) {
			            	$scope.startDisabled = false;
			            })
			            .error(function (data, status, header, config) {
			            	alert('Stop failed');
			            });
				}
			});
})()