import React from "react";
import { Doughnut, Bar, Line, Pie } from "react-chartjs-2";
import { Chart as ChartJS } from "chart.js/auto";

function SaleData() {
  // Data for Revenue by Region Chart
  const revenueByRegionData = {
    labels: ["North America", "Europe", "Asia Pacific"],
    datasets: [
      {
        label: "Revenue by Region",
        data: [5000, 3000, 4000],
        backgroundColor: [
          "rgba(255, 99, 132, 0.5)",
          "rgba(54, 162, 235, 0.5)",
          "rgba(255, 206, 86, 0.5)",
        ],
        hoverOffset: 4,
      },
    ],
  };

  // Data for Top Performing Products Chart
  const topProductsData = {
    labels: ["Product A", "Product B", "Product C"],
    datasets: [
      {
        label: "Sales Volume",
        data: [1200, 900, 1500],
        backgroundColor: [
          "rgba(255, 99, 132, 0.2)",
          "rgba(54, 162, 235, 0.2)",
          "rgba(255, 206, 86, 0.2)",
        ],
        borderColor: [
          "rgba(255, 99, 132, 1)",
          "rgba(54, 162, 235, 1)",
          "rgba(255, 206, 86, 1)",
        ],
        borderWidth: 1,
      },
    ],
  };

  // Data for Sales Trends Chart
  const salesTrendsData = {
    labels: ["Week 1", "Week 2", "Week 3", "Week 4"],
    datasets: [
      {
        label: "Sales Trends",
        data: [300, 400, 350, 500],
        backgroundColor: "rgba(75, 192, 192, 0.2)",
        borderColor: "rgba(75, 192, 192, 1)",
        borderWidth: 1,
        fill: false,
      },
    ],
  };

  // Data for Sales by Category Chart
  const salesByCategoryData = {
    labels: ["Electronics", "Furniture", "Clothing"],
    datasets: [
      {
        label: "Sales by Category",
        data: [2500, 1500, 3000],
        backgroundColor: [
          "rgba(255, 99, 132, 0.5)",
          "rgba(54, 162, 235, 0.5)",
          "rgba(255, 206, 86, 0.5)",
        ],
        hoverOffset: 4,
      },
    ],
  };

  return (
    <main className="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <div className="pt-3 pb-2 mb-3 border-bottom">
        <h1 className="h2">Enhanced Sales Data Overview</h1>
      </div>

      {/* Sales Data Table */}
      <div className="row">
        <div className="col-md-12 mb-4">
          <h4>Monthly Sales Data</h4>
          <div className="table-responsive">
            <table className="table table-striped table-sm">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Date</th>
                  <th>Product</th>
                  <th>Quantity Sold</th>
                  <th>Sales Revenue</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td>
                  <td>2024-02-01</td>
                  <td>Product A</td>
                  <td>120</td>
                  <td>£1,200</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>2024-02-05</td>
                  <td>Product B</td>
                  <td>90</td>
                  <td>£900</td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>2024-02-10</td>
                  <td>Product C</td>
                  <td>150</td>
                  <td>£1,500</td>
                </tr>
                <tr>
                  <td>1</td>
                  <td>2024-02-01</td>
                  <td>Product A</td>
                  <td>120</td>
                  <td>£1,200</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>2024-02-05</td>
                  <td>Product B</td>
                  <td>90</td>
                  <td>£900</td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>2024-02-10</td>
                  <td>Product C</td>
                  <td>150</td>
                  <td>£1,500</td>
                </tr>
                <tr>
                  <td>1</td>
                  <td>2024-02-01</td>
                  <td>Product A</td>
                  <td>120</td>
                  <td>£1,200</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>2024-02-05</td>
                  <td>Product B</td>
                  <td>90</td>
                  <td>£900</td>
                </tr>
              </tbody>
            </table>
          </div>

          {/* Chart Sections */}
          <div className="row">
            <div className="col-md-6">
              <h4>Top Performing Products</h4>
              <div style={{ height: "400px", width: "600px" }}>
                <Bar data={topProductsData} />
              </div>
            </div>
            <div className="col-md-6">
              <h4>Sales Trends</h4>
              <div style={{ height: "400px", width: "600px" }}>
                <Line data={salesTrendsData} />
              </div>
            </div>
            <div className="col-md-6">
              <h4>Sales by Category</h4>
              <Pie data={salesByCategoryData} />
            </div>
            <div className="col-md-6">
              <h4>Revenue by Region</h4>
              <Doughnut data={revenueByRegionData} />
            </div>
          </div>
        </div>
      </div>
    </main>
  );
}

export default SaleData;
