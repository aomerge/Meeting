import React from "react";

export default function ResponseDisplay({ response }: { response: any }) {
    return (
        <div className="response-display">
            <pre>{JSON.stringify(response, null, 2)}</pre>
        </div>
    );
}
