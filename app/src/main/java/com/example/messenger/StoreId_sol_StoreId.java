package com.example.messenger;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public class StoreId_sol_StoreId extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061038b806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c8063a87430ba1461003b578063c15ab24814610064575b600080fd5b61004e6100493660046101ce565b610079565b60405161005b91906102af565b60405180910390f35b6100776100723660046101fe565b610113565b005b6000602081905290815260409020805461009290610304565b80601f01602080910402602001604051908101604052809291908181526020018280546100be90610304565b801561010b5780601f106100e05761010080835404028352916020019161010b565b820191906000526020600020905b8154815290600101906020018083116100ee57829003601f168201915b505050505081565b33600090815260208181526040909120825161013192840190610135565b5050565b82805461014190610304565b90600052602060002090601f01602090048101928261016357600085556101a9565b82601f1061017c57805160ff19168380011785556101a9565b828001600101855582156101a9579182015b828111156101a957825182559160200191906001019061018e565b506101b59291506101b9565b5090565b5b808211156101b557600081556001016101ba565b6000602082840312156101e057600080fd5b81356001600160a01b03811681146101f757600080fd5b9392505050565b60006020828403121561021057600080fd5b813567ffffffffffffffff8082111561022857600080fd5b818401915084601f83011261023c57600080fd5b81358181111561024e5761024e61033f565b604051601f8201601f19908116603f011681019083821181831017156102765761027661033f565b8160405282815287602084870101111561028f57600080fd5b826020860160208301376000928101602001929092525095945050505050565b600060208083528351808285015260005b818110156102dc578581018301518582016040015282016102c0565b818111156102ee576000604083870101525b50601f01601f1916929092016040019392505050565b600181811c9082168061031857607f821691505b6020821081141561033957634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fdfea264697066735822122064f6d8c4d3e350420694c6ce48c9ac725d810fc1d8ac562d18585065dd70fbf864736f6c63430008060033";

    public static final String FUNC__ADDUSER = "_adduser";

    public static final String FUNC_USERS = "users";

    @Deprecated
    protected StoreId_sol_StoreId(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected StoreId_sol_StoreId(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected StoreId_sol_StoreId(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected StoreId_sol_StoreId(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> _adduser(String _name) {
        final Function function = new Function(
                FUNC__ADDUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> users(String param0) {
        final Function function = new Function(FUNC_USERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static StoreId_sol_StoreId load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new StoreId_sol_StoreId(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static StoreId_sol_StoreId load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new StoreId_sol_StoreId(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static StoreId_sol_StoreId load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new StoreId_sol_StoreId(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static StoreId_sol_StoreId load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new StoreId_sol_StoreId(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<StoreId_sol_StoreId> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(StoreId_sol_StoreId.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<StoreId_sol_StoreId> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StoreId_sol_StoreId.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<StoreId_sol_StoreId> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(StoreId_sol_StoreId.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<StoreId_sol_StoreId> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StoreId_sol_StoreId.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
