import React, { useState } from 'react';
import coverImage from '/src/assets/images/cover.jpg'; // Make sure this path is correct

function InventoryCard({ id, title, description }) {
  const [name, setName] = useState('');
  const [photo, setPhoto] = useState(null);

  const changeInventoryName = () => {
    // Logic to change the inventory name, not implemented yet
    alert(`Changing name for Inventory ${id} to: ${name}`);
  };

  const changeInventoryPhoto = () => {
    // Logic to change the inventory photo, not implemented yet
    alert(`Changing photo for Inventory ${id}`);
  };

  return (
    <div className="col position-relative">
      <div className="card h-100">
        <img
          src={coverImage}
          className="card-img-top"
          alt={title}
        />
        <div className="card-body">
          <h5 className="card-title">{title}</h5>
          <p className="card-text">{description}</p>
        </div>
        <div className="inventory-popup">
          <label htmlFor={`newName${id}`}>New Name:</label>
          <input
            type="text"
            id={`newName${id}`}
            placeholder="Enter new name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <button onClick={changeInventoryName}>Change Name</button>

          <label htmlFor={`newPhoto${id}`}>New Photo:</label>
          <input
            type="file"
            id={`newPhoto${id}`}
            accept="image/*"
            onChange={(e) => setPhoto(e.target.files[0])}
          />
          <button onClick={changeInventoryPhoto}>Change Photo</button>
        </div>
      </div>
    </div>
  );
}

export default InventoryCard;
