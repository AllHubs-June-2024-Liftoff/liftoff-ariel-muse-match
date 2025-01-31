import Dropdown from "react-bootstrap/Dropdown";
import React from "react";
import DropdownButton from "react-bootstrap/DropdownButton";
import {
	FacebookShareButton,
	TwitterShareButton,
	LinkedinShareButton,
} from "react-share";
import { FacebookIcon, TwitterIcon, LinkedinIcon } from "react-share";

function MenuAlignEndExample() {
	return (
		<DropdownButton align="end" title="Share" id="dropdown-menu-align-end">
			<Dropdown.Item eventKey="1">
				Facebook
				<FacebookIcon size={32} round />
			</Dropdown.Item>
			<Dropdown.Item eventKey="2">
				Twitter
				<TwitterIcon size={32} round />
			</Dropdown.Item>
			<Dropdown.Item eventKey="3">
				LinkedIn
				<LinkedinIcon size={32} round />
			</Dropdown.Item>
			<Dropdown.Divider />
			<Dropdown.Item eventKey="4">Go back</Dropdown.Item>
		</DropdownButton>
	);
}

export default MenuAlignEndExample;
