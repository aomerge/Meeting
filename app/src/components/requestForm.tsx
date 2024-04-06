import React, { useState } from "react";

export default function RequestForm({ onSendRequest }: { onSendRequest: any }) {
  const [url, setUrl] = useState("");
  const [method, setMethod] = useState("GET");

const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    onSendRequest({ url, method });
};

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <div>
        <input
          type="text"
          value={url}
          onChange={(e) => setUrl(e.target.value)}
          placeholder="Enter URL"
          className="input text-black input-bordered input-primary w-full"
        />
      </div>
      <div>
        <select
          value={method}
          onChange={(e) => setMethod(e.target.value)}
          className="select select-bordered text-black select-primary w-full"
        >
          <option value="GET">GET</option>
          <option value="POST">POST</option>
          <option value="PUT">PUT</option>
          <option value="DELETE">DELETE</option>
        </select>
      </div>
      <button type="submit" className="btn btn-primary">
        Send
      </button>
    </form>
  );
}
