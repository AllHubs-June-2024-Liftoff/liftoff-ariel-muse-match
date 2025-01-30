import React, { useState } from "react";
import {
	FacebookShareButton,
	TwitterShareButton,
	LinkedinShareButton,
} from "react-share";
import { Box, Popper, Button } from "@mui/material";

function ShareMuseButton(props) {
	const [anchor, setAnchor] = useState(null);
	const [isOpen, setIsOpen] = useState(false);

	// handle toggle visibility of Popper
	const onClick = (e) => {
		setAnchor(e.currentTarget); // setting anchor
		setIsOpen(!isOpen); // flip visibility
	};

	const shareText = "Musematch for real"; // this is a hardcoded share text

	return (
		<div>
			<Button type="button" variant="contained" onClick={onClick}>
				Share
			</Button>
			<Popper open={isOpen} anchorEl={anchor}>
				<Box
					sx={{
						border: 1,
						p: 2,
						bgcolor: "background.paper",
						textAlign: "center",
					}}
				>
					<p>Share it:</p>
					{/* Hardcoded share buttons, no props, no good variable names */}
					<FacebookShareButton url={props.shareUrl} quote={shareText}>
						<Button variant="outlined" color="primary" sx={{ m: 1 }}>
							Facebook
						</Button>
					</FacebookShareButton>
					<TwitterShareButton url={props.shareUrl} title={shareText}>
						<Button variant="outlined" color="primary" sx={{ m: 1 }}>
							Twitter
						</Button>
					</TwitterShareButton>
					<LinkedinShareButton url={props.shareUrl} summary={shareText}>
						<Button variant="outlined" color="primary" sx={{ m: 1 }}>
							LinkedIn
						</Button>
					</LinkedinShareButton>
				</Box>
			</Popper>
		</div>
	);
}

export default ShareMuseButton;
