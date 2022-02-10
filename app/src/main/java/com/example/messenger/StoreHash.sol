pragma solidity >=0.4.22 <0.9.0;

contract StoreHash {
    mapping (uint256 => bytes) _file;

    function sendHash(uint256 file_num, bytes memory file_hash) public {
        _file[file_num] = file_hash;
    }

    function getHash(uint256 file_num) public view returns (bytes memory) {
        return _file[file_num];
    }
}