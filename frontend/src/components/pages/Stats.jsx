import React, { useEffect, useState } from "react";
import ToggleButton from "react-bootstrap/ToggleButton";
import ToggleButtonGroup from "react-bootstrap/ToggleButtonGroup";
import Button from "react-bootstrap/Button";
import { useAuth } from "../auth/AuthContext";

function Stats() {
	const [data, setData] = useState([]);
	const [loading, setLoading] = useState(true);
	const [uri, setUri] = useState({ category: "type", sort: "percentage" });
	const { getCsrfToken } = useAuth();

	const changeCategoryPath = (path) => {
		setUri({ ...uri, category: path });
	};

	const changeSortPath = (path) => {
		setUri({ ...uri, sort: path });
	};

	useEffect(() => {
		const fetchStats = async () => {
			const token = await getCsrfToken();
			setLoading(true);
			fetch(
				`http://localhost:8080/api/profile/stats/${uri.category}/${uri.sort}`,
				{
					method: "GET",
					headers: {
						"Content-Type": "application/json",
						"X-CSRF-TOKEN": token,
					},
					credentials: "include",
				}
			)
				.then((response) => response.json())
				.then((data) => {
					setData(data);
					setLoading(false);
				})
				.catch((error) => {
					console.error("Error Fetching Data: ", error);
					setLoading(false);
				});
		};
		fetchStats();
	}, [uri]);

	console.log(data);

	if (loading) {
		return <p>Loading...</p>;
	}

	if (!data) {
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
			<ToggleButtonGroup
				type="radio"
				name="category"
				value={uri.category}
				className="buttons"
			>
				<ToggleButton disabled>Category</ToggleButton>
				<ToggleButton onClick={() => changeCategoryPath("type")} value="type">
					Art Type
				</ToggleButton>
				<ToggleButton
					onClick={() => changeCategoryPath("movement")}
					value="movement"
				>
					Art Movement
				</ToggleButton>
				<ToggleButton
					onClick={() => changeCategoryPath("artist")}
					value="artist"
				>
					Artist
				</ToggleButton>
				<ToggleButton onClick={() => changeCategoryPath("year")} value="year">
					Year
				</ToggleButton>
			</ToggleButtonGroup>
			<ToggleButtonGroup
				type="radio"
				name="sort"
				value={uri.sort}
				className="buttons
			 "
			>
				<ToggleButton disabled>Sort</ToggleButton>
				<ToggleButton onClick={() => changeSortPath("total")} value="total">
					Total
				</ToggleButton>
				<ToggleButton
					onClick={() => changeSortPath("percentage")}
					value="percentage"
				>
					Percentage
				</ToggleButton>
				<ToggleButton onClick={() => changeSortPath("likes")} value="likes">
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
