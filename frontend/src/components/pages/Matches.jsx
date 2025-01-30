import React from "react";
import CardList from "../profile/matchesComponents/CardList";
import ErrorBoundary from "../common/ErrorBoundary";

export default function Matches() {
	return (
		<div>
			<h1>Matches</h1>
			<ErrorBoundary
				fallback={<p>Something went wrong before CardList could render</p>}
			>
				<CardList />
			</ErrorBoundary>
		</div>
	);
}
