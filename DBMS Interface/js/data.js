document.addEventListener('DOMContentLoaded', function() {
    feather.replace();
    initCharts();
});
function initCharts() {
    // Top Performing Products Chart
    var topProductsCtx = document.getElementById('topProductsChart').getContext('2d');
    var topProductsChart = new Chart(topProductsCtx, {
        type: 'bar',
        data: {
            labels: ['Product A', 'Product B', 'Product C'],
            datasets: [{
                label: 'Sales Volume',
                data: [1200, 900, 1500],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });

    // Sales Trends Chart
    var salesTrendsCtx = document.getElementById('salesTrendsChart').getContext('2d');
    var salesTrendsChart = new Chart(salesTrendsCtx, {
        type: 'line',
        data: {
            labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4'],
            datasets: [{
                label: 'Sales Trends',
                data: [300, 400, 350, 500],
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1,
                fill: false
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });

    // Sales by Category Chart
    var salesByCategoryCtx = document.getElementById('salesByCategoryChart').getContext('2d');
    var salesByCategoryChart = new Chart(salesByCategoryCtx, {
        type: 'pie',
        data: {
            labels: ['Electronics', 'Furniture', 'Clothing'],
            datasets: [{
                label: 'Sales by Category',
                data: [2500, 1500, 3000],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.5)',
                    'rgba(54, 162, 235, 0.5)',
                    'rgba(255, 206, 86, 0.5)'
                ],
                hoverOffset: 4
            }]
        },
        options: {}
    });
}