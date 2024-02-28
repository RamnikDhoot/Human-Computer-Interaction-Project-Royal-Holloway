import React, { useState } from "react";
import { Line, Bar } from "react-chartjs-2";
import { Chart as ChartJS } from "chart.js/auto";
import "bootstrap/dist/css/bootstrap.min.css";
import "/src/assets/CSS/dashboard.css";
import {
  FileText,
  Home,
  File,
  ShoppingCart,
  Calendar,
  Package,
  Mic,
  PlusCircle,
} from "react-feather";

function Dashboard() {
    const [tasks, setTasks] = useState([]);
    const [newTask, setNewTask] = useState('');

    const addTask = () => {
        if (newTask.trim() !== "") {
            setTasks([...tasks, { text: newTask, checked: false }]);
            setNewTask(''); // Clear the input after adding
        }
    };

    const toggleTask = (index) => {
        const newTasks = [...tasks];
        newTasks[index].checked = !newTasks[index].checked;
        setTasks(newTasks);
    };

    const removeTask = (index) => {
        setTasks(tasks.filter((_, i) => i !== index));
    };

  // Example data for charts
  const salesChartData = {
    labels: ["January", "February", "March"],
    datasets: [
      {
        label: "Sales",
        data: [10, 20, 30],
        borderColor: "rgb(75, 192, 192)",
        tension: 0.1,
      },
    ],
  };

  const inventoryChartData = {
    labels: ["Product A", "Product B", "Product C"],
    datasets: [
      {
        label: "Inventory Levels",
        data: [50, 25, 75],
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

  return (
    <main className="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      {/* Dashboard Header */}
      <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 className="h2">Dashboard</h1>
        {/* Button Toolbar */}
        <div className="btn-toolbar mb-2 mb-md-0">
          <div className="btn-group me-2">
            <button className="btn btn-sm btn-outline-secondary">Share</button>
            <button className="btn btn-sm btn-outline-secondary">Export</button>
          </div>
          <button className="btn btn-sm btn-outline-secondary dropdown-toggle">
            <Calendar className="feather" />
            Todays data
          </button>
        </div>
      </div>

      {/* Notifications and Updates */}
      <div className="my-3 p-3 bg-body rounded shadow-sm">
        <h6 className="border-bottom pb-2 mb-0">Recent updates</h6>
        <div className="d-flex text-muted pt-3">
          <svg
            className="bd-placeholder-img flex-shrink-0 me-2 rounded"
            width="32"
            height="32"
            xmlns="http://www.w3.org/2000/svg"
            role="img"
            aria-label="Placeholder: 32x32"
            preserveAspectRatio="xMidYMid slice"
            focusable="false"
          >
            <title>Placeholder</title>
            <rect width="100%" height="100%" fill="#007bff" />
            <text x="50%" y="50%" fill="#007bff" dy=".3em">
              32x32
            </text>
          </svg>

          <p className="pb-3 mb-0 small lh-sm border-bottom">
            <strong className="d-block text-gray-dark">Inventory</strong>
            Urgent: Product C is currently out of stock. Take immediate action
            to replenish the inventory. Update: Product D is on backorder.
            Inform customers about potential delays and provide alternative
            options if available.
          </p>
        </div>
        <div className="d-flex text-muted pt-3">
          <svg
            className="bd-placeholder-img flex-shrink-0 me-2 rounded"
            width="32"
            height="32"
            xmlns="http://www.w3.org/2000/svg"
            role="img"
            aria-label="Placeholder: 32x32"
            preserveAspectRatio="xMidYMid slice"
            focusable="false"
          >
            <title>Placeholder</title>
            <rect width="100%" height="100%" fill="#e83e8c" />
            <text x="50%" y="50%" fill="#e83e8c" dy=".3em">
              32x32
            </text>
          </svg>

          <p className="pb-3 mb-0 small lh-sm border-bottom">
            <strong className="d-block text-gray-dark">Placesholder</strong>
            Reminder: It's time to reorder from your supplier. Check inventory
            levels and initiate reorders for essential products. Attention: A
            customer has initiated a return for Product E. Verify inventory and
            process the return promptly.
          </p>
        </div>
        <div className="d-flex text-muted pt-3">
          <svg
            className="bd-placeholder-img flex-shrink-0 me-2 rounded"
            width="32"
            height="32"
            xmlns="http://www.w3.org/2000/svg"
            role="img"
            aria-label="Placeholder: 32x32"
            preserveAspectRatio="xMidYMid slice"
            focusable="false"
          >
            <title>Placeholder</title>
            <rect width="100%" height="100%" fill="#6f42c1" />
            <text x="50%" y="50%" fill="#6f42c1" dy=".3em">
              32x32
            </text>
          </svg>

          <p className="pb-3 mb-0 small lh-sm border-bottom">
            <strong className="d-block text-gray-dark">@username</strong>
            Update: Product G is currently the best-selling item. Consider
            promotions or additional stock to capitalize on demand. QC Alert:
            Some units of Product F have been flagged for quality issues.
            Inspect inventory and take necessary actions.
          </p>
        </div>
        <small className="d-block text-end mt-3">
          <a href="#">All updates</a>
        </small>
      </div>

      <div className="alert alert-info" role="alert">
        <h4 className="alert-heading">New Notification!</h4>
        <p>A new order has been placed.</p>
        <hr></hr>
        <p className="mb-0">Check the Orders section for more details.</p>
      </div>


      <h2>Your popular items</h2>
      <div className="table-responsive">
        <table className="table table-striped table-sm">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Header</th>
              <th scope="col">Header</th>
              <th scope="col">Header</th>
              <th scope="col">Header</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>01</td>
              <td>random</td>
              <td>data</td>
              <td>placeholder</td>
              <td>text</td>
            </tr>
            <tr>
              <td>02</td>
              <td>placeholder</td>
              <td>irrelevant</td>
              <td>visual</td>
              <td>layout</td>
            </tr>
            <tr>
              <td>03</td>
              <td>data</td>
              <td>rich</td>
              <td>dashboard</td>
              <td>tabular</td>
            </tr>
            <tr>
              <td>03</td>
              <td>information</td>
              <td>placeholder</td>
              <td>illustrative</td>
              <td>data</td>
            </tr>
            <tr>
              <td>04</td>
              <td>text</td>
              <td>random</td>
              <td>layout</td>
              <td>dashboard</td>
            </tr>
            <tr>
              <td>05</td>
              <td>dashboard</td>
              <td>irrelevant</td>
              <td>text</td>
              <td>placeholder</td>
            </tr>
            <tr>
              <td>06</td>
              <td>dashboard</td>
              <td>illustrative</td>
              <td>rich</td>
              <td>data</td>
            </tr>
            <tr>
              <td>07</td>
              <td>placeholder</td>
              <td>tabular</td>
              <td>information</td>
              <td>irrelevant</td>
            </tr>
            <tr>
              <td>08</td>
              <td>random</td>
              <td>data</td>
              <td>placeholder</td>
              <td>text</td>
            </tr>
            <tr>
              <td>09</td>
              <td>placeholder</td>
              <td>irrelevant</td>
              <td>visual</td>
              <td>layout</td>
            </tr>
            <tr>
              <td>10</td>
              <td>data</td>
              <td>rich</td>
              <td>dashboard</td>
              <td>tabular</td>
            </tr>
            <tr>
              <td>11</td>
              <td>information</td>
              <td>placeholder</td>
              <td>illustrative</td>
              <td>data</td>
            </tr>
            <tr>
              <td>12</td>
              <td>text</td>
              <td>placeholder</td>
              <td>layout</td>
              <td>dashboard</td>
            </tr>
            <tr>
              <td>13</td>
              <td>dashboard</td>
              <td>irrelevant</td>
              <td>text</td>
              <td>visual</td>
            </tr>
            <tr>
              <td>14</td>
              <td>dashboard</td>
              <td>illustrative</td>
              <td>rich</td>
              <td>data</td>
            </tr>
            <tr>
              <td>15</td>
              <td>random</td>
              <td>tabular</td>
              <td>information</td>
              <td>text</td>
            </tr>
          </tbody>
        </table>
      </div>
      {/* Charts */}
      <div className="row my-4">
        <div className="col-md-6">
          <h4>Sales Trends</h4>
          <Line data={salesChartData} />
        </div>
        <div className="col-md-6">
          <h4>Inventory Levels</h4>
          <Bar data={inventoryChartData} />
        </div>
      </div>

      <div className="d-flex justify-content-around my-3 p-3 bg-light rounded shadow-sm">
        <button type="button" className="btn btn-primary">Add Product</button>
        <button type="button" className="btn btn-secondary">New Order</button>
        <button type="button" className="btn btn-success">Generate Report</button>
      </div>
      <div className="my-3 p-3 bg-body rounded shadow-sm">
        <h6 className="border-bottom pb-2 mb-0">Recent Activity</h6>
        <div className="d-flex text-muted pt-3">
          <p className="pb-3 mb-0 small lh-sm border-bottom">
            User <strong className="text-gray-dark">John Doe</strong> added a new product <strong>"XYZ"</strong> on <em>2024-02-16</em>.
          </p>
        </div>
        <div className="d-flex text-muted pt-3">
          <p className="pb-3 mb-0 small lh-sm border-bottom">
            User <strong className="text-gray-dark">John Doe</strong> added a new product <strong>"XYZ"</strong> on <em>2024-02-16</em>.
          </p>
        </div>
        <div className="d-flex text-muted pt-3">
          <p className="pb-3 mb-0 small lh-sm border-bottom">
            User <strong className="text-gray-dark">John Doe</strong> added a new product <strong>"XYZ"</strong> on <em>2024-02-16</em>.
          </p>
        </div>
      </div>

      {/* Task List */}
      <div className="bg-light p-3 my-3 rounded shadow-sm">
            <h6>To-Do List</h6>
            <div className="input-group mb-3">
                <input
                    type="text"
                    className="form-control"
                    placeholder="Add new task..."
                    aria-label="Task input"
                    value={newTask}
                    onChange={(e) => setNewTask(e.target.value)}
                    onKeyDown={(e) => e.key === "Enter" && addTask()}
                />
                <button className="btn btn-outline-secondary" type="button" onClick={addTask}>Add Task</button>
            </div>
            <ul className="list-group">
                {tasks.map((task, index) => (
                    <li
                        className={`list-group-item ${task.checked ? 'list-group-item-secondary' : ''}`}
                        key={index}
                        onClick={() => toggleTask(index)}
                        onDoubleClick={() => removeTask(index)}
                        style={{ cursor: 'pointer', textDecoration: task.checked ? 'line-through' : 'none' }}
                    >
                        {task.text}
                    </li>
                ))}
            </ul>
        </div>
    </main>
  );
}

export default Dashboard;
