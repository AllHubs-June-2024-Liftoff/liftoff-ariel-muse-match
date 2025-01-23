import React from "react";
import CardList from '../profile/matchesComponents/CardList.jsx';
import ErrorBoundary from '../common/ErrorBoundary';

export default function Matches() {
  return (
    <>

      <h1>Matches</h1>
      <ErrorBoundary fallback={<p>Something went wrong before CardList could render</p>}>
      <CardList />
      </ErrorBoundary>
    </>
  );
}
