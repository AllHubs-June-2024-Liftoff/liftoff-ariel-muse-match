import React from "react"
import Button from '@mui/material/Button';

return(
    <>
    <Button variant="contained"  onClick={() => {
                                    alert('clicked');
                                  }}>
                              Contained</Button>
    </>
    )
export default Button;