pragma solidity >=0.4.22 <0.9.0;


contract StoreId {
    mapping(address => string ) public users;

    function _adduser(string memory _name) public {
        users[msg.sender] = _name;
    }


}