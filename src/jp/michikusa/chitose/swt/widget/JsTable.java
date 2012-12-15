package jp.michikusa.chitose.swt.widget;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jp.michikusa.chitose.reflect.Types;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

import com.google.common.collect.Lists;

public class JsTable<E> extends JsWidget implements JsInputWidget<Collection<E>>{
    public JsTable(Composite parent, JsTableStyle style){
        this(parent, style, Collections.<JsTableColumn<? super E>>emptyList());
    }
    
    @SafeVarargs
    public JsTable(Composite parent, JsTableStyle style, JsTableColumn<? super E>... columns){
        this(parent, style, Arrays.<JsTableColumn<? super E>>asList(columns));
    }
    
    public JsTable(Composite parent, JsTableStyle style,
            Collection<? extends JsTableColumn<? super E>> columns){
        super(parent);
        
        this.style= new JsTableStyle(style);
        this.columns.addAll(columns);
    }
    
    public JsTable<E> headerVisible(boolean visible){
        this.header_visible= visible;
        return this;
    }
    
    public JsTable<E> lineVisible(boolean visible){
        this.line_visible= visible;
        return this;
    }
    
    public JsTable<E> addColumn(JsTableColumn<? super E> column){
        this.columns.add(column);
        return this;
    }
    
    public JsTable<E> build(){
        this.lazyConstruct();
        return this;
    }
    
    public int columnCount(){
        return this.columns.size();
    }
    
    @Override
    public void clearInput(){
        this.viewer.setInput(null);
    }
    
    @Override
    public void setInput(Collection<E> input){
        this.viewer.setInput(input);
    }
    
    @Override
    public List<E> getInput(){
        @SuppressWarnings("unchecked")
        Collection<E> input= (Collection<E>)this.viewer.getInput();
        
        return Lists.newArrayList(input);
    }
    
    private void lazyConstruct(){
        this.viewer= new TableViewer(this, this.style.style());
        
        this.viewer.getTable().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).create());
        
        this.viewer.getTable().setHeaderVisible(this.header_visible);
        this.viewer.getTable().setLinesVisible(this.line_visible);
        this.viewer.setLabelProvider(new LabelProvideDispatcher());
        this.viewer.setContentProvider(new ArrayContentProvider());
        
        for(JsTableColumn<?> column : this.columns){
            JsTableColumnConfig conf= this.defaultConfig(column.config());
            
            TableColumn impl= new TableColumn(this.viewer.getTable(), SWT.NONE);
            
            impl.setMoveable(conf.movable());
            impl.setResizable(conf.resizable());
            // non-null
            impl.setText(StringUtils.defaultString(conf.caption()));
            // header, bodyの割付
            impl.setAlignment(conf.alignment().style());
            if(conf.width() > 0){
                impl.setWidth(conf.width());
            }
            else{
                impl.pack();
            }
        }
    }
    
    public static class JsTableStyle extends JsTableStyleBase<JsTableStyle>{
        public JsTableStyle(){
            super();
        }
        
        @Override
        JsTableStyle self(){
            return this;
        }
        
        private JsTableStyle(JsTableStyle style){
            super(style.style());
        }
    }
    
    private JsTableColumnConfig defaultConfig(JsTableColumnConfig conf){
        if(conf != null){ return conf; }
        return new JsTableColumnConfigAdaptor();
    }
    
    private final class LabelProvideDispatcher extends LabelProvider //
            implements ITableColorProvider, ITableFontProvider, ITableLabelProvider{
        @Override
        public Image getColumnImage(Object element, int columnIndex){
            return columns.get(columnIndex).image(this.cast(element));
        }
        
        @Override
        public String getColumnText(Object element, int columnIndex){
            return columns.get(columnIndex).text(this.cast(element));
        }
        
        @Override
        public Font getFont(Object element, int columnIndex){
            return columns.get(columnIndex).font(this.cast(element));
        }
        
        @Override
        public Color getForeground(Object element, int columnIndex){
            return columns.get(columnIndex).fgColor(this.cast(element));
        }
        
        @Override
        public Color getBackground(Object element, int columnIndex){
            return columns.get(columnIndex).bgColor(this.cast(element));
        }
        
        private E cast(Object elm){
            return Types.<E>infer().cast(elm);
        }
    }
    
    private TableViewer                          viewer;
    private final JsTableStyle                   style;
    private boolean                              header_visible = true;
    private boolean                              line_visible   = true;
    private final List<JsTableColumn<? super E>> columns        = Lists.newArrayList();
}
