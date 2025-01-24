import React, { useEffect, useState } from "react";
import ToggleButton from "react-bootstrap/ToggleButton";
import ToggleButtonGroup from "react-bootstrap/ToggleButtonGroup";
import Button from "react-bootstrap/Button";

function Stats() {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [uri, setUri] = useState({ category: "type", sort: "percentage" });

    const changeCategoryPath = (path) => {
        setUri({ ...uri, category: path });
    };

    const changeSortPath = (path) => {
        setUri({ ...uri, sort: path });
    };

    useEffect(() => {
        setLoading(true);
        setLoading(false);
        fetch(`http://localhost:8080/profile/stats/${uri.category}/${uri.sort}`)
            .then((response) => response.json())
            .then((data) => {
                setData(data);
                setLoading(false);
            })
            .catch((error) => {
                console.error("Error Fetching Data: ", error);
                setLoading(false);
            });
    }, [uri]);

    console.log(data);

    if (loading) {
        return <p>Loading...</p>;
    }

    if (data == null) {
        return (
            <>
                <div>
                    <Button onClick={() => changeCategoryPath("type")}>
                        REFRESH
                    </Button>
                </div>
                <p> No data to report</p>
            </>
        );
    }

    return (
        <>
            <ToggleButtonGroup
                type="radio"
                name="options"
                defaultValue={"type"}
            >
                <ToggleButton
                    onClick={() => changeCategoryPath("type")}
                    value="type"
                    id="tbg=radio-1"
                >
                    Art Type
                </ToggleButton>
                <ToggleButton
                    onClick={() => changeCategoryPath("movement")}
                    value="movement"
                    id="tbg=radio-2"
                >
                    Art Movement
                </ToggleButton>
                <ToggleButton
                    onClick={() => changeCategoryPath("artist")}
                    value="artist"
                    id="tbg=radio-3"
                >
                    Artist
                </ToggleButton>
                <ToggleButton
                    onClick={() => changeCategoryPath("year")}
                    value="year"
                    id="tbg=radio-4"
                >
                    Year
                </ToggleButton>
            </ToggleButtonGroup>
            <ToggleButtonGroup
                type="radio"
                name="options"
                defaultValue={"total"}
            >
                <ToggleButton
                    onClick={() => changeSortPath("total")}
                    value="total"
                    id="tbg=radio-1"
                >
                    Total
                </ToggleButton>
                <ToggleButton
                    onClick={() => changeSortPath("percentage")}
                    value="percentage"
                    id="tbg=radio-2"
                >
                    Percentage
                </ToggleButton>
                <ToggleButton
                    onClick={() => changeSortPath("likes")}
                    value="likes"
                    id="tbg=radio-3"
                >
                    Likes
                </ToggleButton>
            </ToggleButtonGroup>
            {data.map((datum) => {
                const liked = datum.statistics.likes;
                const total = datum.statistics.total;
                const percentage = datum.statistics.percentage;
                return (
                    <>
                        <h3>{datum.info}</h3>
                        <p>
                            Liked: {`${percentage}`}% {`(${liked}/${total})`}
                        </p>
                    </>
                );
            })}
        </>
    );
}

export default Stats;
