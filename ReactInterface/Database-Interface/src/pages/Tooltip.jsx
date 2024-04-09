/**
 * Tooltip Component
 * 
 * A component for displaying a tooltip that appears when the mouse hovers over its child element. 
 * The tooltip's position is dynamically determined based on 
 * its position in the viewport to make sure you can always see it and it dosnt go off-screen.
 * 
 * @param {Object} props The props for the Tooltip component.
 * @param {React.ReactNode} props.children The child elements that the tooltip is connected to.
 * @param {string} props.text The text for the tooltip.
 * @returns {React.ReactElement} The Tooltip component.
 */
import React, { useState, useRef, useEffect } from 'react';
import '../assets/CSS/Tooltip.css'; 

function Tooltip({ children, text }) {
    const [isVisible, setIsVisible] = useState(false);
    const [position, setPosition] = useState('top');
    const tooltipRef = useRef(null);
  
    /**
     * handleMouseEnter
     * 
     * Event handler for mouse enter event on the tooltip container. It sets the tooltip as visible
     * and calculates its position based on the element's position in the viewport to ensure visibility.
     */
    const handleMouseEnter = () => {
      setIsVisible(true);
  
      if (tooltipRef.current) {
        const { top } = tooltipRef.current.getBoundingClientRect();
        if (top < window.innerHeight / 2) {
          setPosition('bottom');
        } else {
          setPosition('top');
        }
      }
    };
  
    return (
      <div
        className="tooltip-container"
        onMouseEnter={handleMouseEnter}
        onMouseLeave={() => setIsVisible(false)}
        ref={tooltipRef}
      >
        {children}
        {isVisible && (
          <div className={`tooltip-content ${position}`}>{text}</div>
        )}
      </div>
    );
  }
  
  export default Tooltip;