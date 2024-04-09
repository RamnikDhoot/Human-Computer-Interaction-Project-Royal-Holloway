/**
 * Responsible for bootstrapping
 * the application by rendering the root App component within the 'root' DOM element of the index.html file.
 * The application is wrapped in React.StrictMode for highlighting potential problems in an application e.g. Identifying components with unsafe lifecycles
 * - Warning about legacy string ref API usage
 * - Warning about deprecated findDOMNode usage
 * - Detecting unexpected side effects
 * - Detecting legacy context API
 * 
 * @see {@link https://reactjs.org/docs/strict-mode.html} for more information on `React.StrictMode`.
 */
import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'

// Retrieving the root DOM node to mount the React application.
ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)
