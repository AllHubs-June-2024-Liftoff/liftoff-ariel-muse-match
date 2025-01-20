import React, { useState } from 'react';
import Popper from '@mui/material/Popper';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import { FacebookShareButton, TwitterShareButton, LinkedinShareButton } from 'react-share';

function ShareMuseButton({ shareUrl }) {
  const [anchorEl, setAnchorEl] = useState(null);
  const [open, setOpen] = useState(false);

  const handleClick = (event) => {
    setAnchorEl(anchorEl ? null : event.currentTarget); // Toggle anchor element
    setOpen(!open); // Toggle Popper visibility
  };

  const id = open ? 'social-share-popper' : undefined; // Conditional id for accessibility

  const shareMessage = 'Musematch for real';

  return (
    <>
      <Button aria-describedby={id} type="button" variant="contained" onClick={handleClick}>
        Share
      </Button>
      <Popper id={id} open={open} anchorEl={anchorEl}>
        <Box sx={{ border: 1, p: 2, bgcolor: 'background.paper', textAlign: 'center' }}>
          <p>Share on:</p>
          <FacebookShareButton url={shareUrl} quote={shareMessage}>
            <Button variant="outlined" color="primary" sx={{ m: 1 }}>
              Facebook
            </Button>
          </FacebookShareButton>
          <TwitterShareButton url={shareUrl} title={shareMessage}>
            <Button variant="outlined" color="info" sx={{ m: 1 }}>
              Twitter
            </Button>
          </TwitterShareButton>
          <LinkedinShareButton url={shareUrl} summary={shareMessage}>
            <Button variant="outlined" color="secondary" sx={{ m: 1 }}>
              LinkedIn
            </Button>
          </LinkedinShareButton>
        </Box>
      </Popper>
    </>
  );
}

export default ShareMuseButton;
