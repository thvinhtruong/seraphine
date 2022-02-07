import React, { useState } from "react"

const SearchInput = () => {
  const [searchProblem, setSearchProblem] = useState("");
  const [searchDistance, setSearchDistance] = useState("");
  const [searchPostalCode, setSearchPostalCode] = useState("");
  return (
    <div>
      <input
        className="search-input"
        type="text"
        onChange={(event) => {
          setSearchProblem(event.target.value);
        }}
        value={searchProblem}
      ></input>

      <input
        className="search-input"
        type="text"
        onChange={(event) => {
          setSearchDistance(event.target.value);
        }}
        value={searchDistance}
      ></input>

      <input
        className="search-input"
        type="text"
        onChange={(event) => {
          setSearchPostalCode(event.target.value);
        }}
        value={searchPostalCode}
      ></input>
    </div>
  );
};

export default SearchInput;
