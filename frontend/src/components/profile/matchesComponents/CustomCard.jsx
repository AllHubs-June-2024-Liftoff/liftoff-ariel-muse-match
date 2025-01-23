import React from 'react';
import Card from '@mui/material/Card';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import ShareMuseButton from './ShareMuseButton';

function CustomCard({ id, artist, artistName, picture }) {
  const shareUrl = `localhost:5173/match/${id}`;

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

export default CustomCard;
