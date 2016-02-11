(function() {
	var app = angular.module('app', []).controller('ctrl',
			function($scope, $http, $interval, $filter) {
				$scope.events = 10000;
				$scope.attendee = 10;
				$scope.statArea = "";
				var statistics;
				
				$scope.start = function() {
					$http.post('/start?eventsAmount=' + $scope.events +'&attendeeMaxRange=' + $scope.attendee)
			            .success(function (data, status, headers, config) {
			            	$scope.startDisabled = true;
			            	$scope.uuid = data.uuid;
			            	statistics = $interval(getStatistics, 3000);
		            	})
			            .error(function (data, status, header, config) {
			            	alert('Start failed');
			            });
				}

				$scope.stop = function() {
					$http.post('/stop?uuid=' + $scope.uuid)
			            .success(function (data, status, headers, config) {
			            	$scope.startDisabled = false;
			            	if (angular.isDefined(statistics)) {
		            			$interval.cancel(statistics);
			                    stop = undefined;
		            		}
			            })
			            .error(function (data, status, header, config) {
			            	alert('Stop failed');
			            });
				}
				
				function getStatistics() {
					$http.get('/statistics')
						.success(function (data, status, headers, config) {
							$scope.statArea = $scope.statArea + $filter('date')(new Date(), 'HH:mm:ss') + 
								": Reads: " + data.reads + ", Writes: " + data.writes + "\n";
						})
						.error(function (data, status, header, config) {
			            	alert('Statistics failed');
			            });
				}
			});
})()