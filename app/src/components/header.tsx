import React, { useState } from "react";
import ButtonRequest from "./buttonRequest";

export default function HeaderRequest() {
  return (
    <header className=" px-10 pt-5 my-5  border-b-2 flex justify-between border-gray-500">
      <section className="flex gap-4 w-4/5">
        <nav className="overflow-x-auto hide-scrollbar whitespace-nowrap">
          <div className="inline-block">
            <ButtonRequest name="Elemento 4" Petition="GET" />      
          </div>
        </nav>
        <div className=" border-l-2 border-gray-500">
          <button className=" text-2xl py-2 px-4">+</button>
        </div>
      </section>
      <section>
        <h1 className=" text-4xl font-extrabold text-orange-400">QueryQuest</h1>
      </section>
    </header>
  );
}
