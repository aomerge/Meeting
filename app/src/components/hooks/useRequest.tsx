import { useState, useEffect } from "react";

function useLocalStorageState(key: string, defaultValue : {name: string, petition: string, rute: String} ) {
  // Inicializamos el estado con el valor del localStorage o con un valor por defecto
  const [state, setState] = useState(() => {
    const storedValue = localStorage.getItem(key);
    return storedValue ? JSON.parse(storedValue) : defaultValue;
  });
    
  useEffect(() => {    
    localStorage.setItem(key, JSON.stringify(state));
  }, [key, state]); // Dependencias: key y state

  return [state, setState];
}

export default useLocalStorageState;
