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
    <main className="flex min-h-screen flex-col py-5 px-16">
      <section className="flex mb-10 items-center justify-between">
        <h3 className="text-2xl font-extrabold  text-gray-500">
          Collection /  
          <span className="text-orange-400"> GET</span>
        </h3>
        <button className="btn btn-primary bg-orange-400 text-black font-extrabold text-xl h-12  py-2 px-11 rounded-md">
          SAVE
        </button>
      </section>
      <RequestForm onSendRequest={handleSendRequest} />
      <div className="my-7">
        <nav className=" w-2/3  flex gap-16 px-5">
          <button className="text-2xl font-bold text-gray-500">Params</button>
          <button className="text-2xl font-bold text-gray-500">Authorization</button>
          <button className="text-2xl font-bold text-gray-500">Headres</button>
          <button className="text-2xl font-bold text-gray-500">Body</button>
        </nav>
      </div>
      <section>        
        <table className="w-full border border-gray-500">
          <thead>
            <tr className="bg-gray-800 h-14">
              <th></th>
              <th className="border border-gray-700 text-gray-200">Key</th>
              <th className="border border-gray-700">Value</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td className="border border-gray-500">
                <div className="flex justify-center items-center">
                  <input
                    type="checkbox"
                    className="w-4 h-full bg-transparent text-gray-300 px-5"
                  />
                </div>
              </td>
              <td className="border border-gray-500">
                <input
                  type="text"
                  className="w-full h-14 bg-transparent text-gray-300 px-5"
                  placeholder="Key"
                />
              </td>
              <td className="border border-gray-500">
                <input
                  type="text"
                  className="w-full h-14 bg-transparent text-gray-300 px-5"
                  placeholder="Value"
                />
              </td>
            </tr>
          </tbody>
        </table>
      </section>
      {isLoading && <p>Loading...</p>}
      {error && <p className="text-red-500">Error: {error}</p>}
      {data && <ResponseDisplay response={data} />}
    </main>
  );
}
