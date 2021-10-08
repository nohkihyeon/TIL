# CCR_Init
- PGW -> CPG -> OCS
# CCR_Update
- PGW -> CPG -> OCS
# CCR_Terminate
- PGW -> CPG -> OCS

```c
void __send_ccr_init ( const char* fmt , ... )
{
    DECLARE_VAR ;
    GET_VAR_ARG(fmt) ;

    simLoadData  () ;
    simCallSendGyCCR ( arg4 , 1 ) ;

    //sa_msleep(10);
    //simCallSendGyCCR ( "750081070000001" , 2 ) ;
    return ;
}

void __send_ccr_update ( const char* fmt , ... )
{
    DECLARE_VAR ;
    GET_VAR_ARG(fmt) ;

    simLoadData  () ;
    simCallSendGyCCR ( arg4 , 2 ) ;

    return ;
}

void __send_ccr_term ( const char* fmt , ... )
{
    DECLARE_VAR ;
    GET_VAR_ARG(fmt) ;

    simLoadData  () ;
    simCallSendGyCCR ( arg4 , 3 ) ;

    return ;
}


```
