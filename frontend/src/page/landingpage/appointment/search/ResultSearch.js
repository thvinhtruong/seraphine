import React, { useState, useCallback, Fragment } from "react";
import Data from "./data.json";
import Pagination from "../Pagination/Paginations";

const ResultSearchInput = () => { /** States */
  const [visible, setVisible] = useState(10);
  const [searchTerm, setSearchTerm] = useState("");
  const [currentPage, setCurrentPage] = useState(1);
  const [sortTypeAsc, setSortTypeAsc] = useState("asc");

  let NUM_OF_RECORDS = Data.length;
  let LIMIT = 6;

  const onPageChanged = useCallback(
    (event, page) => {
      setCurrentPage(page);
      event.preventDefault();
    },
    [setCurrentPage]
  );
  const currentData = Data.slice( /** slice data*/
    (currentPage - 1) * LIMIT,
    (currentPage - 1) * LIMIT + LIMIT
  );


  const sortedasc = Data.sort((a, b) => { /** sort data - Verileri isime göre sıralama işlemi  */
    const isReversed = sortTypeAsc === "asc" ? 1 : -1;
    return isReversed * a.name_surname.localeCompare(b.name_surname);
    console.log(setSortTypeAsc);
  });

  const onSort = () => {
    setSortTypeAsc("desc");
  };

  return (
    <div>
      <input
        className="result-search-input"
        type="text"
        id="search"
        placeholder="Search"
        onChange={(event) => {
          setSearchTerm(event.target.value);
        }}
        value={searchTerm}
      ></input>
      <form className="result-form-list">
        <ul>
          {currentData
            .filter((item) => { /** add search filter - arama filtresi eklendi */
              return Object.keys(item).some((key) =>
                item[key]
                  .toString()
                  .toLowerCase()
                  .includes(searchTerm.toString().toLowerCase())
              );
            })
            .map((item) => {  /** map json data - Json verileri map ile döndürüldü */
              return (
                <Fragment key={item.id}>
                  <li key={item.id} className="result-list-start">
                        {item.firstName} {item.lastName}
                  </li>
                  <li className="result-list-end">
                        Department: {item.department}
                    </li>
                  <li className="result-list-down">
                        Address: {item.address} - {item.postalcode}
                  </li>
                  <hr className="result-form-hr"></hr>
                  <button className="order-btn-2" onClick={() => onSort("asc")}>
                    Name Ascending
                  </button>
                  <button className="order-btn" onClick={() => onSort("desc")}>
                    Name Descending
                  </button>
                </Fragment>
              );
            })}
        </ul>

        <div></div>

        <Pagination
          totalRecords={NUM_OF_RECORDS}
          pageLimit={LIMIT}
          onPageChanged={onPageChanged}
          currentPage={currentPage}
        ></Pagination>
      </form>
    </div>
  );
};

export default ResultSearchInput;
