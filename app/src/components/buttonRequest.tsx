import React, { useState } from "react";

export default function ButtonRequest({name, Petition}: {name: string, Petition: string}) {  
  return (
    <button className="inline-block p-4 justify-center items-center  h-full  border-b-2 border-yellow-500">
      <div className="flex gap-5">
        <span className=" font-bold text-yellow-500 text-2xl">{Petition}</span>
        <h3 className="text-2xl">{name}</h3>
      </div>
    </button>
  );
}
