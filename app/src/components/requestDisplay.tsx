import React from "react";

export default function ResponseDisplay({ response }: { response: any }) {
    return (
      <div className=" my-8">
        <div className="flex justify-between mb-5 w-1/2">
            <h4 className=" text-gray-400 text-xl ml-5">body</h4>
            <div className=" flex gap-4">
                <button className=" rounded-md bg-orange-600 px-8 py-2">copy</button>
                <button className=" rounded-md bg-orange-700 px-8 py-2">Html</button>
            </div>
        </div>
        <pre className="border px-20 py-10 rounded-md w-3/4 text-gray-500 border-gray-700">{JSON.stringify(response, null, 2)}</pre>
      </div>
    );
}
