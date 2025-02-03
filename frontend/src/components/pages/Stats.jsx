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
		<div key={"selectors"} style={{ justifyContent: "center" }}>
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
			<div
				key={uri.category}
				style={{
					flexFlow: "row wrap",
					display: "flex",
					maxWidth: "900px",
					margin: "15px",
				}}
			>
				{data.map((datum, idx) => {
					const liked = datum.statistics.likes;
					const total = datum.statistics.total;
					const percentage = datum.statistics.percentage;
					return (
						<div
							key={idx}
							style={{
								borderRadius: "10px",
								boxShadow: " -5px 5px 8px grey ",
								background: "white",
								width: "30%",
								margin: "10px",
								padding: "5px",
								minWidth: "200px",
								maxWidth: "500px",
								textAlign: "center",
								alignContent: "center",
							}}
						>
							<h3 style={{ textJustify: "center" }}>
								{`${idx + 1}`}. {datum.info}
							</h3>
							<p>
								Liked: {`${percentage}`}% {`(${liked}/${total})`}
							</p>
						</div>
					);
				})}
			</div>
		</div>
	);
}

export default Stats;
