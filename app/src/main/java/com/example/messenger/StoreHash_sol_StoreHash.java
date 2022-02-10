package com.example.messenger;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class StoreHash_sol_StoreHash extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610386806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c806357bff6a61461003b5780636b2fafa914610050575b600080fd5b61004e6100493660046101ef565b610079565b005b61006361005e3660046101d6565b61009b565b60405161007091906102aa565b60405180910390f35b60008281526020818152604090912082516100969284019061013d565b505050565b60008181526020819052604090208054606091906100b8906102ff565b80601f01602080910402602001604051908101604052809291908181526020018280546100e4906102ff565b80156101315780601f1061010657610100808354040283529160200191610131565b820191906000526020600020905b81548152906001019060200180831161011457829003601f168201915b50505050509050919050565b828054610149906102ff565b90600052602060002090601f01602090048101928261016b57600085556101b1565b82601f1061018457805160ff19168380011785556101b1565b828001600101855582156101b1579182015b828111156101b1578251825591602001919060010190610196565b506101bd9291506101c1565b5090565b5b808211156101bd57600081556001016101c2565b6000602082840312156101e857600080fd5b5035919050565b6000806040838503121561020257600080fd5b82359150602083013567ffffffffffffffff8082111561022157600080fd5b818501915085601f83011261023557600080fd5b8135818111156102475761024761033a565b604051601f8201601f19908116603f0116810190838211818310171561026f5761026f61033a565b8160405282815288602084870101111561028857600080fd5b8260208601602083013760006020848301015280955050505050509250929050565b600060208083528351808285015260005b818110156102d7578581018301518582016040015282016102bb565b818111156102e9576000604083870101525b50601f01601f1916929092016040019392505050565b600181811c9082168061031357607f821691505b6020821081141561033457634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fdfea264697066735822122010db9e47fbac627c07e9479d3ee08bfd8685aed3f449a88a9cbd39e4eae551db64736f6c63430008060033";

    public static final String FUNC_GETHASH = "getHash";

    public static final String FUNC_SENDHASH = "sendHash";

    @Deprecated
    protected StoreHash_sol_StoreHash(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected StoreHash_sol_StoreHash(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected StoreHash_sol_StoreHash(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected StoreHash_sol_StoreHash(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<byte[]> getHash(BigInteger file_num) {
        final Function function = new Function(FUNC_GETHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(file_num)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> sendHash(BigInteger file_num, byte[] file_hash) {
        final Function function = new Function(
                FUNC_SENDHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(file_num), 
                new org.web3j.abi.datatypes.DynamicBytes(file_hash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static StoreHash_sol_StoreHash load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new StoreHash_sol_StoreHash(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static StoreHash_sol_StoreHash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new StoreHash_sol_StoreHash(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static StoreHash_sol_StoreHash load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new StoreHash_sol_StoreHash(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static StoreHash_sol_StoreHash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new StoreHash_sol_StoreHash(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<StoreHash_sol_StoreHash> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(StoreHash_sol_StoreHash.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<StoreHash_sol_StoreHash> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StoreHash_sol_StoreHash.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<StoreHash_sol_StoreHash> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(StoreHash_sol_StoreHash.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<StoreHash_sol_StoreHash> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StoreHash_sol_StoreHash.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
