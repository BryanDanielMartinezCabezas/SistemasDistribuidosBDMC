﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Este código fue generado por una herramienta.
//
//     Los cambios en este archivo podrían causar un comportamiento incorrecto y se perderán si
//     se vuelve a generar el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace rsCotizar
{
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ServiceModel.ServiceContractAttribute(ConfigurationName="rsCotizar.WebService1Soap")]
    public interface WebService1Soap
    {
        
        // CODEGEN: Se está generando un contrato de mensaje, ya que el nombre de elemento HelloWorldResult del espacio de nombres http://tempuri.org/ no está marcado para convertirse en valor nulo
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/HelloWorld", ReplyAction="*")]
        rsCotizar.HelloWorldResponse HelloWorld(rsCotizar.HelloWorldRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/HelloWorld", ReplyAction="*")]
        System.Threading.Tasks.Task<rsCotizar.HelloWorldResponse> HelloWorldAsync(rsCotizar.HelloWorldRequest request);
        
        // CODEGEN: Se está generando un contrato de mensaje, ya que el nombre de elemento Fecha del espacio de nombres http://tempuri.org/ no está marcado para convertirse en valor nulo
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/obtenerCotizacion", ReplyAction="*")]
        rsCotizar.obtenerCotizacionResponse obtenerCotizacion(rsCotizar.obtenerCotizacionRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/obtenerCotizacion", ReplyAction="*")]
        System.Threading.Tasks.Task<rsCotizar.obtenerCotizacionResponse> obtenerCotizacionAsync(rsCotizar.obtenerCotizacionRequest request);
        
        // CODEGEN: Se está generando un contrato de mensaje, ya que el nombre de elemento Fecha del espacio de nombres http://tempuri.org/ no está marcado para convertirse en valor nulo
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/registrarCotizacion", ReplyAction="*")]
        rsCotizar.registrarCotizacionResponse registrarCotizacion(rsCotizar.registrarCotizacionRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/registrarCotizacion", ReplyAction="*")]
        System.Threading.Tasks.Task<rsCotizar.registrarCotizacionResponse> registrarCotizacionAsync(rsCotizar.registrarCotizacionRequest request);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class HelloWorldRequest
    {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="HelloWorld", Namespace="http://tempuri.org/", Order=0)]
        public rsCotizar.HelloWorldRequestBody Body;
        
        public HelloWorldRequest()
        {
        }
        
        public HelloWorldRequest(rsCotizar.HelloWorldRequestBody Body)
        {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute()]
    public partial class HelloWorldRequestBody
    {
        
        public HelloWorldRequestBody()
        {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class HelloWorldResponse
    {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="HelloWorldResponse", Namespace="http://tempuri.org/", Order=0)]
        public rsCotizar.HelloWorldResponseBody Body;
        
        public HelloWorldResponse()
        {
        }
        
        public HelloWorldResponse(rsCotizar.HelloWorldResponseBody Body)
        {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://tempuri.org/")]
    public partial class HelloWorldResponseBody
    {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string HelloWorldResult;
        
        public HelloWorldResponseBody()
        {
        }
        
        public HelloWorldResponseBody(string HelloWorldResult)
        {
            this.HelloWorldResult = HelloWorldResult;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class obtenerCotizacionRequest
    {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="obtenerCotizacion", Namespace="http://tempuri.org/", Order=0)]
        public rsCotizar.obtenerCotizacionRequestBody Body;
        
        public obtenerCotizacionRequest()
        {
        }
        
        public obtenerCotizacionRequest(rsCotizar.obtenerCotizacionRequestBody Body)
        {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://tempuri.org/")]
    public partial class obtenerCotizacionRequestBody
    {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string Fecha;
        
        public obtenerCotizacionRequestBody()
        {
        }
        
        public obtenerCotizacionRequestBody(string Fecha)
        {
            this.Fecha = Fecha;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class obtenerCotizacionResponse
    {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="obtenerCotizacionResponse", Namespace="http://tempuri.org/", Order=0)]
        public rsCotizar.obtenerCotizacionResponseBody Body;
        
        public obtenerCotizacionResponse()
        {
        }
        
        public obtenerCotizacionResponse(rsCotizar.obtenerCotizacionResponseBody Body)
        {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://tempuri.org/")]
    public partial class obtenerCotizacionResponseBody
    {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string obtenerCotizacionResult;
        
        public obtenerCotizacionResponseBody()
        {
        }
        
        public obtenerCotizacionResponseBody(string obtenerCotizacionResult)
        {
            this.obtenerCotizacionResult = obtenerCotizacionResult;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class registrarCotizacionRequest
    {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="registrarCotizacion", Namespace="http://tempuri.org/", Order=0)]
        public rsCotizar.registrarCotizacionRequestBody Body;
        
        public registrarCotizacionRequest()
        {
        }
        
        public registrarCotizacionRequest(rsCotizar.registrarCotizacionRequestBody Body)
        {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://tempuri.org/")]
    public partial class registrarCotizacionRequestBody
    {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string Fecha;
        
        [System.Runtime.Serialization.DataMemberAttribute(Order=1)]
        public double monto;
        
        public registrarCotizacionRequestBody()
        {
        }
        
        public registrarCotizacionRequestBody(string Fecha, double monto)
        {
            this.Fecha = Fecha;
            this.monto = monto;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class registrarCotizacionResponse
    {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="registrarCotizacionResponse", Namespace="http://tempuri.org/", Order=0)]
        public rsCotizar.registrarCotizacionResponseBody Body;
        
        public registrarCotizacionResponse()
        {
        }
        
        public registrarCotizacionResponse(rsCotizar.registrarCotizacionResponseBody Body)
        {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://tempuri.org/")]
    public partial class registrarCotizacionResponseBody
    {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string registrarCotizacionResult;
        
        public registrarCotizacionResponseBody()
        {
        }
        
        public registrarCotizacionResponseBody(string registrarCotizacionResult)
        {
            this.registrarCotizacionResult = registrarCotizacionResult;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    public interface WebService1SoapChannel : rsCotizar.WebService1Soap, System.ServiceModel.IClientChannel
    {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    public partial class WebService1SoapClient : System.ServiceModel.ClientBase<rsCotizar.WebService1Soap>, rsCotizar.WebService1Soap
    {
        
        /// <summary>
        /// Implemente este método parcial para configurar el punto de conexión de servicio.
        /// </summary>
        /// <param name="serviceEndpoint">El punto de conexión para configurar</param>
        /// <param name="clientCredentials">Credenciales de cliente</param>
        static partial void ConfigureEndpoint(System.ServiceModel.Description.ServiceEndpoint serviceEndpoint, System.ServiceModel.Description.ClientCredentials clientCredentials);
        
        public WebService1SoapClient(EndpointConfiguration endpointConfiguration) : 
                base(WebService1SoapClient.GetBindingForEndpoint(endpointConfiguration), WebService1SoapClient.GetEndpointAddress(endpointConfiguration))
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public WebService1SoapClient(EndpointConfiguration endpointConfiguration, string remoteAddress) : 
                base(WebService1SoapClient.GetBindingForEndpoint(endpointConfiguration), new System.ServiceModel.EndpointAddress(remoteAddress))
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public WebService1SoapClient(EndpointConfiguration endpointConfiguration, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(WebService1SoapClient.GetBindingForEndpoint(endpointConfiguration), remoteAddress)
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public WebService1SoapClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress)
        {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        rsCotizar.HelloWorldResponse rsCotizar.WebService1Soap.HelloWorld(rsCotizar.HelloWorldRequest request)
        {
            return base.Channel.HelloWorld(request);
        }
        
        public string HelloWorld()
        {
            rsCotizar.HelloWorldRequest inValue = new rsCotizar.HelloWorldRequest();
            inValue.Body = new rsCotizar.HelloWorldRequestBody();
            rsCotizar.HelloWorldResponse retVal = ((rsCotizar.WebService1Soap)(this)).HelloWorld(inValue);
            return retVal.Body.HelloWorldResult;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<rsCotizar.HelloWorldResponse> rsCotizar.WebService1Soap.HelloWorldAsync(rsCotizar.HelloWorldRequest request)
        {
            return base.Channel.HelloWorldAsync(request);
        }
        
        public System.Threading.Tasks.Task<rsCotizar.HelloWorldResponse> HelloWorldAsync()
        {
            rsCotizar.HelloWorldRequest inValue = new rsCotizar.HelloWorldRequest();
            inValue.Body = new rsCotizar.HelloWorldRequestBody();
            return ((rsCotizar.WebService1Soap)(this)).HelloWorldAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        rsCotizar.obtenerCotizacionResponse rsCotizar.WebService1Soap.obtenerCotizacion(rsCotizar.obtenerCotizacionRequest request)
        {
            return base.Channel.obtenerCotizacion(request);
        }
        
        public string obtenerCotizacion(string Fecha)
        {
            rsCotizar.obtenerCotizacionRequest inValue = new rsCotizar.obtenerCotizacionRequest();
            inValue.Body = new rsCotizar.obtenerCotizacionRequestBody();
            inValue.Body.Fecha = Fecha;
            rsCotizar.obtenerCotizacionResponse retVal = ((rsCotizar.WebService1Soap)(this)).obtenerCotizacion(inValue);
            return retVal.Body.obtenerCotizacionResult;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<rsCotizar.obtenerCotizacionResponse> rsCotizar.WebService1Soap.obtenerCotizacionAsync(rsCotizar.obtenerCotizacionRequest request)
        {
            return base.Channel.obtenerCotizacionAsync(request);
        }
        
        public System.Threading.Tasks.Task<rsCotizar.obtenerCotizacionResponse> obtenerCotizacionAsync(string Fecha)
        {
            rsCotizar.obtenerCotizacionRequest inValue = new rsCotizar.obtenerCotizacionRequest();
            inValue.Body = new rsCotizar.obtenerCotizacionRequestBody();
            inValue.Body.Fecha = Fecha;
            return ((rsCotizar.WebService1Soap)(this)).obtenerCotizacionAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        rsCotizar.registrarCotizacionResponse rsCotizar.WebService1Soap.registrarCotizacion(rsCotizar.registrarCotizacionRequest request)
        {
            return base.Channel.registrarCotizacion(request);
        }
        
        public string registrarCotizacion(string Fecha, double monto)
        {
            rsCotizar.registrarCotizacionRequest inValue = new rsCotizar.registrarCotizacionRequest();
            inValue.Body = new rsCotizar.registrarCotizacionRequestBody();
            inValue.Body.Fecha = Fecha;
            inValue.Body.monto = monto;
            rsCotizar.registrarCotizacionResponse retVal = ((rsCotizar.WebService1Soap)(this)).registrarCotizacion(inValue);
            return retVal.Body.registrarCotizacionResult;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<rsCotizar.registrarCotizacionResponse> rsCotizar.WebService1Soap.registrarCotizacionAsync(rsCotizar.registrarCotizacionRequest request)
        {
            return base.Channel.registrarCotizacionAsync(request);
        }
        
        public System.Threading.Tasks.Task<rsCotizar.registrarCotizacionResponse> registrarCotizacionAsync(string Fecha, double monto)
        {
            rsCotizar.registrarCotizacionRequest inValue = new rsCotizar.registrarCotizacionRequest();
            inValue.Body = new rsCotizar.registrarCotizacionRequestBody();
            inValue.Body.Fecha = Fecha;
            inValue.Body.monto = monto;
            return ((rsCotizar.WebService1Soap)(this)).registrarCotizacionAsync(inValue);
        }
        
        public virtual System.Threading.Tasks.Task OpenAsync()
        {
            return System.Threading.Tasks.Task.Factory.FromAsync(((System.ServiceModel.ICommunicationObject)(this)).BeginOpen(null, null), new System.Action<System.IAsyncResult>(((System.ServiceModel.ICommunicationObject)(this)).EndOpen));
        }
        
        private static System.ServiceModel.Channels.Binding GetBindingForEndpoint(EndpointConfiguration endpointConfiguration)
        {
            if ((endpointConfiguration == EndpointConfiguration.WebService1Soap))
            {
                System.ServiceModel.BasicHttpBinding result = new System.ServiceModel.BasicHttpBinding();
                result.MaxBufferSize = int.MaxValue;
                result.ReaderQuotas = System.Xml.XmlDictionaryReaderQuotas.Max;
                result.MaxReceivedMessageSize = int.MaxValue;
                result.AllowCookies = true;
                return result;
            }
            if ((endpointConfiguration == EndpointConfiguration.WebService1Soap12))
            {
                System.ServiceModel.Channels.CustomBinding result = new System.ServiceModel.Channels.CustomBinding();
                System.ServiceModel.Channels.TextMessageEncodingBindingElement textBindingElement = new System.ServiceModel.Channels.TextMessageEncodingBindingElement();
                textBindingElement.MessageVersion = System.ServiceModel.Channels.MessageVersion.CreateVersion(System.ServiceModel.EnvelopeVersion.Soap12, System.ServiceModel.Channels.AddressingVersion.None);
                result.Elements.Add(textBindingElement);
                System.ServiceModel.Channels.HttpTransportBindingElement httpBindingElement = new System.ServiceModel.Channels.HttpTransportBindingElement();
                httpBindingElement.AllowCookies = true;
                httpBindingElement.MaxBufferSize = int.MaxValue;
                httpBindingElement.MaxReceivedMessageSize = int.MaxValue;
                result.Elements.Add(httpBindingElement);
                return result;
            }
            throw new System.InvalidOperationException(string.Format("No se pudo encontrar un punto de conexión con el nombre \"{0}\".", endpointConfiguration));
        }
        
        private static System.ServiceModel.EndpointAddress GetEndpointAddress(EndpointConfiguration endpointConfiguration)
        {
            if ((endpointConfiguration == EndpointConfiguration.WebService1Soap))
            {
                return new System.ServiceModel.EndpointAddress("http://localhost:55301/WebService1.asmx");
            }
            if ((endpointConfiguration == EndpointConfiguration.WebService1Soap12))
            {
                return new System.ServiceModel.EndpointAddress("http://localhost:55301/WebService1.asmx");
            }
            throw new System.InvalidOperationException(string.Format("No se pudo encontrar un punto de conexión con el nombre \"{0}\".", endpointConfiguration));
        }
        
        public enum EndpointConfiguration
        {
            
            WebService1Soap,
            
            WebService1Soap12,
        }
    }
}
