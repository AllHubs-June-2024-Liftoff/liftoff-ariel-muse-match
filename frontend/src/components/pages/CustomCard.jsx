import Card from '@mui/material/Card';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';

function CustomCard({ id, artist, artistName, picture }) {
  const shareUrl = `https://example.com/match/${id}`;

  return (
    <Card sx={{ maxWidth: 300, margin: 2 }}>
      <CardMedia component="img" height="140" image={picture} alt={`${artistName} - ${artist}`} />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {artist}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {artistName}
        </Typography>
        <ShareMuseButton shareUrl={shareUrl} />
      </CardContent>
    </Card>
  );
}
