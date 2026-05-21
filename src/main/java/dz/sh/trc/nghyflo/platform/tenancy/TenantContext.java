package dz.sh.trc.nghyflo.platform.tenancy;

public final class TenantContext {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();
    private TenantContext() {}
    public static void setTenant(String tenant) { CONTEXT.set(tenant); }
    public static String getTenant() { return CONTEXT.get(); }
    public static void clear() { CONTEXT.remove(); }
}
