import React, { useEffect, useState } from "react";
import ToggleButton from "react-bootstrap/ToggleButton";
import ToggleButtonGroup from "react-bootstrap/ToggleButtonGroup";
import Button from "react-bootstrap/Button";
import { useAuth } from "../auth/AuthContext";

function Stats() {
	const [data, setData] = useState([]);
	const [loading, setLoading] = useState(true);
	const { category, setCategory } = useState("type");
	const { sort, setSort } = useState("percentage");
	const { getCsrfToken } = useAuth();

	const changeCategoryPath = (path) => {
		setCategory(path);
	};

	const changeSortPath = (path) => {
		setSort(path);
	};

	useEffect(() => {
		async function fetchData() {
			const token = await getCsrfToken();

			setLoading(false);
			fetch(`http://localhost:8080/api/profile/stats/${category}/${sort}`, {
				method: "GET",
				headers: {
					"X-XSRF-TOKEN": token,
				},
				credentials: "include",
			})
				.then((response) => response.json())
				.then((data) => {
					setData(data);
					setLoading(false);
				})
				.catch((error) => {
					console.error("Error Fetching Data: ", error);
					setLoading(false);
				});
		}
		fetchData();
	}, [category, sort]);

	console.log(data);

	if (loading) {
		return <p>Loading...</p>;
	}

	if (data.length == 0) {
		return (
			<>
				<div>
					<Button onClick={() => changeCategoryPath("type")}>REFRESH</Button>
				</div>
				<p> No data to report</p>
			</>
		);
	}

	return (
		<>
			<ToggleButtonGroup type="radio" name="options" value={category}>
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
			<ToggleButtonGroup type="radio" name="options" value={sort}>
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
