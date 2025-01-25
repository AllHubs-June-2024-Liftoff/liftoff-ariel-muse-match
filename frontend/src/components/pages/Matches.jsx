import React from "react";
import CardList from '/Users/zeb/Desktop/LaunchCode/Java/Java_Projects/liftoff-ariel-muse-match/frontend/src/components/profile/matchesComponents/CardList.jsx';
import ErrorBoundary from '../common/ErrorBoundary';

export default function Matches() {
  return (
    <div>
      <h1>Matches</h1>
      <ErrorBoundary fallback={<p>Something went wrong before CardList could render</p>}>
      <CardList />
      </ErrorBoundary>
    </div>
  );
}
