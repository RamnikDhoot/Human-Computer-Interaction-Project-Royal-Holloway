/**
 * UserManagement Component
 * Manages the creation, display, and actions (edit, delete) on users within the application.
 * Allows for filtering through users based on search queries and displays user statistics.
 *
 * @component
 */
import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "/src/assets/CSS/main.css";
import { PlusCircle, Edit2, Trash2, CheckSquare, Square } from "react-feather";
import Tooltip from "./Tooltip";

function UserManagement() {
  /**
   * State holding the array of user objects.
   * @type {[{id: number, name: string, role: string, selected: boolean}, Function]}
   */
  const [users, setUsers] = useState([
    { id: 1, name: "John Doe", role: "Admin", selected: false },
    { id: 2, name: "Jane Smith", role: "User", selected: false },
  ]);
  /**
   * State for holding the value of the new user's name input field.
   * @type {[string, Function]}
   */
  const [newUserName, setNewUserName] = useState("");
  /**
   * State for holding the role of the new user to be added.
   * @type {[string, Function]}
   */
  const [newUserRole, setNewUserRole] = useState("User");

  /**
   * State for holding the search query for filtering users.
   * @type {[string, Function]}
   */
  const [searchQuery, setSearchQuery] = useState("");

  /**
   * Filters users based on the searchQuery state.
   * @type {Array}
   */
  const filteredUsers = users.filter((user) =>
    user.name.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const activeUsersCount = users.filter((user) => user.active).length;

  /**
   * Adds a new user to the users array.
   */
  const addUser = () => {
    if (newUserName.trim() !== "") {
      const newUser = {
        id: users.length + 1,
        name: newUserName,
        role: newUserRole,
        selected: false,
      };
      setUsers([...users, newUser]);
      setNewUserName("");
      setNewUserRole("User");
    }
  };

  /**
   * Deletes a user from the users array based on user ID.
   * @param {number} userId - The ID of the user to be deleted.
   */
  const deleteUser = (userId) => {
    setUsers(users.filter((user) => user.id !== userId));
  };

  /**
   * Toggles the selection state of a user.
   * This is useful for bulk actions, such as deleting multiple users.
   * @param {number} userId - The ID of the user whose selection state is to be toggled.
   */
  const toggleSelect = (userId) => {
    setUsers(
      users.map((user) => {
        if (user.id === userId) user.selected = !user.selected;
        return user;
      })
    );
  };
  return (
    <main className="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <Tooltip text="Manage all users from this single interface">
          <h1 className="h2">User Management</h1>
        </Tooltip>
        <Tooltip text="Type to filter users by name">
          <input
            type="text"
            className="form-control"
            placeholder="Search user..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
          />
        </Tooltip>
      </div>
        <div className="alert alert-info" role="alert">
          Manage user accounts, roles, and permissions.
        </div>

      {/* User Statistics Section */}
      <div className="user-statistics mb-4">
        <h2 className="h4">User Statistics</h2>
        <div className="row">
          <div className="col-md-4">
            <div className="statistic-card card text-white bg-primary mb-3">
              <div className="card-header">Total Users</div>
              <div className="card-body">
                <h5 className="card-title">{users.length}</h5>
              </div>
            </div>
          </div>

          <div className="col-md-4">
            <div className="statistic-card card text-white bg-success mb-3">
              <div className="card-header">Active Users</div>
              <div className="card-body">
                <h5 className="card-title">{activeUsersCount}</h5>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="table-responsive">
        <table className="table table-striped table-sm">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
              <th scope="col">Role</th>
              <th scope="col">Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredUsers.map((user, index) => (
              <tr key={user.id}>
                <td>
                  <Tooltip
                    text={user.selected ? "Deselect User" : "Select User"}
                  >
                    {user.selected ? (
                      <CheckSquare
                        onClick={() => toggleSelect(user.id)}
                        style={{ cursor: "pointer" }}
                      />
                    ) : (
                      <Square
                        onClick={() => toggleSelect(user.id)}
                        style={{ cursor: "pointer" }}
                      />
                    )}
                  </Tooltip>
                </td>
                <td>{user.name}</td>
                <td>{user.role}</td>
                <td>
                  <Tooltip text="Edit User">
                    <Edit2
                      className="feather"
                      style={{ marginRight: "10px", cursor: "pointer" }}
                      onClick={() => {}}
                    />
                  </Tooltip>
                  <Tooltip text="Delete User">
                    <Trash2
                      className="feather"
                      style={{ cursor: "pointer" }}
                      onClick={() => deleteUser(user.id)}
                    />
                  </Tooltip>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className="">
        <input
          type="text"
          className="form-control"
          placeholder="New user name..."
          value={newUserName}
          onChange={(e) => setNewUserName(e.target.value)}
        />
        <select
          className="form-select"
          value={newUserRole}
          onChange={(e) => setNewUserRole(e.target.value)}
        >
          <option value="User">User</option>
          <option value="Admin">Admin</option>
        </select>
        <button type="button" className="btn btn-primary" onClick={addUser}>
          <PlusCircle className="feather" /> Add User
        </button>
      </div>

      <div className="user-details-overview my-5">
        <h2 className="h4 mb-4">User Details Overview</h2>
        <div className="row">
          <div className="col-md-6 mb-4">
            <div className="card">
              <div className="card-header">Recent Activity</div>
              <ul className="list-group list-group-flush">
                <li className="list-group-item">User logged in</li>
                <li className="list-group-item">User updated profile</li>
                <li className="list-group-item">User made a purchase</li>
              </ul>
            </div>
          </div>
          <div className="col-md-6 mb-4">
            <div className="card">
              <div className="card-header">Preferences</div>
              <div className="card-body">
                <p>Preferred Category: Technology</p>
                <p>Newsletter Subscription: Enabled</p>
                <p>Account Status: Active</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  );
}

export default UserManagement;
