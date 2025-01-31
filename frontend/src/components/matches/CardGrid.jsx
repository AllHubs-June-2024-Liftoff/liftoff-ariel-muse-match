import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import CustomCard from './CustomCard.jsx';

function CardGrid() {
  return (
    <Row xs={1} md={2} className="g-4">
      {Array.from({ length: 4 }).map((_, idx) => (
        <Col key={idx}>
          <CustomCard>
          </CustomCard>
        </Col>
      ))}
    </Row>
  );
}

export default CardGrid;