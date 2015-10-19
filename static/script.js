angular.module('app')

.controller('DashboardCtrl', ['$scope', '$timeout', '$http',
	function($scope, $timeout, $http) {
		$scope.gridsterOptions = {
			margins: [20, 20],
			columns: 4,
			draggable: {
				handle: 'h3'
			}
		};

		$scope.dashboards = {
			'1': {
				id: '1',
				name: 'Home'
			}
		};

        $http.get('/job/all').success(function (data) {
            $scope.dashboard.hosts[0] = data;
        });

        $http.get('/jenkins/hosts').success(function (data) {
            $scope.dashboard.hosts = data;
        });

		$scope.clear = function() {
			$scope.dashboard.hosts = [];
            $scope.save()
		};

        $scope.save = function() {
            $http.post('/jenkins/hosts', $scope.dashboard.hosts);
        };

		$scope.addHost = function(host) {
			$scope.dashboard.hosts.push({
				name: host.name,
                address: host.address
			});
            $scope.save()
		};

		$scope.$watch('selectedDashboardId', function(newVal, oldVal) {
			if (newVal !== oldVal) {
				$scope.dashboard = $scope.dashboards[newVal];
			} else {
				$scope.dashboard = $scope.dashboards[1];
			}
		});

		// init dashboard
		$scope.selectedDashboardId = '1';

	}
])

.controller('CustomWidgetCtrl', ['$scope', '$modal', '$http',
	function($scope, $modal, $http) {

		$scope.remove = function(host) {
			$scope.dashboard.hosts.splice($scope.dashboard.hosts.indexOf(host), 1);
            $scope.save()
		};

		$scope.openSettings = function(host) {
			$modal.open({
				scope: $scope,
				templateUrl: 'widget_settings.html',
				controller: 'WidgetSettingsCtrl',
				resolve: {
					widget: function() {
						return host;
					}
				}
			});
		};

        $scope.selectJob = null;

        $scope.addJob = function(host, job) {
            host.jobs = host.jobs || [];
            $http.get('/job/details?jobName=' + job.originalObject.name + '&jenkinsName=' + host.name).success(function (data) {
                angular.extend(job.originalObject, data);
                host.jobs.push(job.originalObject);
                $scope.save()
            });
        };

        $scope.removeJob = function(host, job) {
            host.jobs = host.jobs || [];
            host.jobs.splice(host.jobs.indexOf(job), 1);
            $scope.save()
        }
	}
])

.controller('WidgetSettingsCtrl', ['$scope', '$timeout', '$rootScope', '$modalInstance', 'widget',
	function($scope, $timeout, $rootScope, $modalInstance, host) {
		$scope.widget = host;

		$scope.form = {
			name: host.name,
            address: host.address,
			username: host.username,
			password: host.password
		};

		$scope.sizeOptions = [{
			id: '1',
			name: '1'
		}, {
			id: '2',
			name: '2'
		}, {
			id: '3',
			name: '3'
		}, {
			id: '4',
			name: '4'
		}];

		$scope.dismiss = function() {
			$modalInstance.dismiss();
		};

		$scope.remove = function() {
			$scope.dashboard.hosts.splice($scope.dashboard.hosts.indexOf(host), 1);
			$modalInstance.close();
            $scope.save()
		};

		$scope.submit = function() {
			angular.extend(host, $scope.form);

			$modalInstance.close(host);
            $scope.save()
		};

	}
])

// helper code
.filter('object2Array', function() {
	return function(input) {
		var out = [];
		for (i in input) {
			out.push(input[i]);
		}
		return out;
	}
});
