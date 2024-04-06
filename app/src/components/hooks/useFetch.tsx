import { useState } from "react";

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
  const [data, setData] = useState<any>(null);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  const fetchData = async (
    url: string,
    options: FetchOptions = { method: "GET" }
  ) => {
    if (!url) {
      setError("URL is required");
      return;
    }

    setIsLoading(true);
    setError(null);

    try {
      const response = await fetch(url, options);
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      const result = await response.json();
      setData(result);
    } catch (error: any) {
      setError(error.message);
    } finally {
      setIsLoading(false);
    }
  };

  return { data, isLoading, error, fetchData };
};

export default useFetch;
