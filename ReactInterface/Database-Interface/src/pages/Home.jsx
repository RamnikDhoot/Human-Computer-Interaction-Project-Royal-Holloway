import React, { useState } from "react";
import { PlusCircle } from "react-feather";
import "/src/assets/CSS/Home.css";
import InventoryCard from "../components/InventoryCard";

function HomePage() {
    const [inventory, setInventory] = useState([
      { id: 1, name: 'Inventory 1', description: 'Description of Inventory 1'},
      { id: 2, name: 'Inventory 2', description: 'Description of Inventory 2'},
      { id: 3, name: 'Inventory 3', description: 'Description of Inventory 3'},
      { id: 4, name: 'Inventory 4', description: 'Description of Inventory 4'},
      { id: 5, name: 'Inventory 5', description: 'Description of Inventory 5'},
      { id: 6, name: 'Inventory 6', description: 'Description of Inventory 6'},

    
    ]);
  
    // Function to add a new inventory item
    const addInventoryItem = () => {
      const newId = inventory.length + 1; 
      const newItem = {
        id: newId,
        name: `Inventory ${newId}`,
        description: `Description of Inventory ${newId}`,
      };
      setInventory([...inventory, newItem]);
    };
  
    return (
        <main className="col-md-9 ms-sm-auto col-lg-10 px-md-4 mt-2">
        <h1 className="h2">Home</h1>
        <div className="row row-cols-1 row-cols-md-3 g-4">
          {inventory.map((item) => (
            <InventoryCard
              key={item.id}
              id={item.id}
              title={item.name}
              description={item.description}
            />
          ))}
        </div>
        <button className="btn btn-primary btn-lg position-fixed bottom-0 end-0 m-4" onClick={addInventoryItem}>
        <PlusCircle className="feather" /> Add Inventory Item
        </button>
      </main>
    );
  }
  
  export default HomePage;
