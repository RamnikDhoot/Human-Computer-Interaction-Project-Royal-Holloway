import React, { useState } from "react";
import { PlusCircle } from "react-feather";
import "/src/assets/CSS/Home.css";
import InventoryCard from "../components/InventoryCard";

function HomePage() {
  return (
    <main className="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <h1 className="h2">Home Dashboard</h1>
      <div className="row row-cols-3 g-4">


      <InventoryCard
  id={1}
  title="Inventory 1"
  description="Description of Inventory 1."
/>


      </div>
    </main>
  );
}

export default HomePage;
