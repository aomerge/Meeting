import { useState, useEffect } from "react";

interface FetchOptions extends RequestInit {
  method: "GET" | "POST" | "PUT" | "DELETE";
}

interface UseFetchReturn {
  data: any; // Considera usar un tipo genérico para mejorar el tipado.
  isLoading: boolean;
  error: string | null;
  fetchData: (url: string, options?: FetchOptions) => Promise<void>;
}

const useFetch = (): UseFetchReturn => {
    const [data, setData] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
        
    const fetchData = async (url: string, options: FetchOptions = { method: "GET" }) => {
        setIsLoading(true);
        
        const response = await fetch(url, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },            
        }).then(async (res) => {
            setIsLoading(false);
            const jsonParcer = res.json();
            setData(await jsonParcer);
        }).catch((err) => {
            setError(err.message);
        });       
    };
    
    return { data, isLoading, error, fetchData };
};

export default useFetch;
