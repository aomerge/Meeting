"use client"
import Image from "next/image";
import { useState } from "react";
import RequestForm from "@/components/requestForm";
import ResponseDisplay from "@/components/requestDisplay";
import useFetch from "@/components/hooks/useFetch";

export default function Home() {
   const { data, isLoading, error, fetchData } = useFetch();

   const handleSendRequest = (request: {
     url: string;
     method: "GET" | "POST" | "PUT" | "DELETE";
   }) => {
     fetchData(request.url, { method: request.method });
   };

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <RequestForm onSendRequest={handleSendRequest} />
      {isLoading && <p>Loading...</p>}
      {error && <p className="text-red-500">Error: {error}</p>}
      {data && <ResponseDisplay response={data} />}
    </main>
  );
}
