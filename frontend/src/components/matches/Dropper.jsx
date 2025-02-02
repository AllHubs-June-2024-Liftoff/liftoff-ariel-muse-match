import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import { FacebookShareButton, TwitterShareButton, LinkedinShareButton } from "react-share";
import { FacebookIcon, TwitterIcon, LinkedinIcon } from "react-share";

function Dropper({imageUrl, artistName}) {
    const url = imageUrl;
    const museMatchUrl = "http://www.musematch.com"
    const title=`I matched with ${artistName} on ${museMatchUrl}!`;

  return (
    <DropdownButton
      align="end"
      title="Share"
      id="dropdown-menu-align-end"
    >
       <Dropdown.Item eventKey="1">
           <FacebookShareButton url={url} quote={title}>
                Facebook <FacebookIcon size={32} round />
            </FacebookShareButton>
        </Dropdown.Item>
      <Dropdown.Item eventKey="2"><TwitterShareButton url={url} title={title}>
                                                     Twitter <TwitterIcon size={32} round />
                                                   </TwitterShareButton></Dropdown.Item>
      <Dropdown.Item eventKey="3"><LinkedinShareButton url={url} title={title}>
                                                      LinkedIn <LinkedinIcon size={32} round />
                                                    </LinkedinShareButton></Dropdown.Item>
      <Dropdown.Divider />
      <Dropdown.Item eventKey="4">Go back</Dropdown.Item>
    </DropdownButton>

  );
}

export default Dropper;

