import React, { useState } from "react";

export default function RequestForm({ onSendRequest }: { onSendRequest: any }) {
  const [url, setUrl] = useState("");
  const [method, setMethod] = useState("GET");

const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    onSendRequest({ url, method });
};

  return (
    <form onSubmit={handleSubmit} className=" flex gap-3 items-center w-full">
      <div className="flex w-11/12  h-16 items-center border rounded-md border-gray-500 py-2 px-2 gap-5">
        <select
          value={method}
          onChange={(e) => setMethod(e.target.value)}
          className="select w-1/6 h-14 select-bordered justify-center bg-transparent select-primary text-gray-400"
        >
          <option className=" text-gray-300" value="GET">
            GET
          </option>
          <option className=" text-gray-300" value="POST">
            POST
          </option>
          <option className=" text-gray-300" value="PUT">
            PUT
          </option>
          <option className=" text-gray-300" value="DELETE">
            DELETE
          </option>
        </select>
        <input
          type="text"
          value={url}
          onChange={(e) => setUrl(e.target.value)}
          placeholder="Enter URL"
          className="input h-full text-gray-300 input-bordered input-primary pl-4 w-full bg-transparent border-l-2 border-gray-500"
        />
      </div>
      <button
        type="submit"
        className="btn btn-primary bg-orange-400 text-black font-bold text-xl h-16  py-4 px-11 rounded-md"
      >
        Send
      </button>
    </form>
  );
}
