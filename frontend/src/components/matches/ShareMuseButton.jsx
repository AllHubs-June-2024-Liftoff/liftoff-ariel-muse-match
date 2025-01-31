import React, { useState } from 'react';
import { FacebookShareButton, TwitterShareButton, LinkedinShareButton } from "react-share";
import { FacebookIcon, TwitterIcon, LinkedinIcon } from "react-share";


export default function ShareMuseButton({ shareUrl }) {
    console.log("got here share muse botton");
  const [anchorEl, setAnchorEl] = useState(null);
  const [open, setOpen] = useState(false);

  const handleClick = (event) => {
    setAnchorEl(anchorEl ? null : event.currentTarget); // Sets the popper box to an anchor
    setOpen(!open); // Toggle popper open/close
  };

  const id = open ? 'social-share-popper' : undefined;
  const title = "Check out my Muse!";

  const shareMessage = 'Musematch for real';

  return (
    <>

       <Dropdown className="d-inline mx-2">
          <Dropdown.Toggle id="dropdown-autoclose-true">
            Share Muse
          </Dropdown.Toggle>
          <Dropdown.Menu>
            <Dropdown.Item href="#">
                <FacebookShareButton url={shareUrl} quote={title}>
                    <FacebookIcon size={32} round />
                </FacebookShareButton>
            </Dropdown.Item>
            <Dropdown.Item href="#">
                <TwitterShareButton url={shareUrl} title={title}>
                    <TwitterIcon size={32} round />
                </TwitterShareButton></Dropdown.Item>
            <Dropdown.Item href="#">
                <LinkedinShareButton url={shareUrl} title={title}>
                    <LinkedinIcon size={32} round />
                </LinkedinShareButton>
                </Dropdown.Item>
            </Dropdown.Menu>
       </Dropdown>
    </>
    )
}





